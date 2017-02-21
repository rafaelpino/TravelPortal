package com.crossover.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.dao.AccountDao;
import com.crossover.dao.TicketDao;
import com.crossover.dao.UserDao;
import com.crossover.dto.Account;
import com.crossover.dto.AirlineOffer;
import com.crossover.dto.AirlineOfferResponse;
import com.crossover.dto.AirlineOffers;
import com.crossover.dto.AirlineTicket;
import com.crossover.dto.AirlineTickets;
import com.crossover.dto.BuyTicketResponse;
import com.crossover.dto.Currency;
import com.crossover.dto.GetBalanceResponse;
import com.crossover.entity.AccountVO;
import com.crossover.entity.TicketVO;
import com.crossover.entity.UserVO;
import com.crossover.service.ForestAPIClient;
import com.crossover.stmp.SMTPProxy;

@RestController("RestUserController")
public class AppController {

	@Autowired
	@Qualifier("usersDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("accountsDao")
	private AccountDao accountDao;
	@Autowired
	@Qualifier("ticketsDao")
	private TicketDao ticketDao;
	@Autowired
	private ForestAPIClient apiClient;
	private static final String APPLICANT_ID = "AlWN8EG";

	/**
	 * GET /get-by-email --> Return the id for the user having the passed email.
	 */
	@RequestMapping("/get-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String userId = "";
		try {
			UserVO user = userDao.findByMail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	/**
	 * Validate user / password
	 */
	@RequestMapping("/login")
	@ResponseBody
	public boolean login(String email, String password) {
		boolean authorized = false;
		try {
			UserVO user = userDao.findByMail(email);
			if (null != password && password.equalsIgnoreCase(user.getPassword())) {
				authorized = true;
			}
		} catch (Exception ex) {
			return false;
		}
		return authorized;
	}

	/**
	 * Validate user / password
	 */
	@RequestMapping("/offers")
	@ResponseBody
	public List<AirlineOfferResponse> getOffers() {
		List<AirlineOfferResponse> response = new ArrayList<>();
		try {
			AirlineOffers offers = apiClient.getAirlineOffers(APPLICANT_ID);
			for (AirlineOffer offer : offers.getOffers()) {
				AirlineOfferResponse offerDTO = new AirlineOfferResponse(offer.getRoute().getFrom(),
						offer.getRoute().getTo(), offer.getPrice().getAmount(), offer.getPrice().getCurrency());
				response.add(offerDTO);
			}
		} catch (Exception ex) {
			return null;
		}
		return response;
	}

	/**
	 * Get bought tickets
	 */
	@RequestMapping("/tickets")
	@ResponseBody
	public List<BuyTicketResponse> getTickets() {
		List<BuyTicketResponse> response = new ArrayList<>();
		try {
			AirlineTickets tickets = apiClient.getTickets(APPLICANT_ID);
			for (AirlineTicket ticket : tickets.getTickets()) {
				BuyTicketResponse tResp = new BuyTicketResponse(ticket.getDetails().getRoute().getFrom(), ticket.getDetails().getRoute().getTo(), ticket.getAmount());
				response.add(tResp);	
			}
		} catch (Exception ex) {
			return null;
		}
		
		return response;
	}
	
	/**
	 * Get bought tickets
	 */
	@RequestMapping("/myTickets")
	@ResponseBody
	public List<BuyTicketResponse> getTicketsByEmail(String email) {
		List<BuyTicketResponse> response = new ArrayList<>();
		try {
			// Find account by email
			UserVO user = userDao.findByMail(email);
			for (TicketVO ticket : user.getUserTickets()) {
				BuyTicketResponse tResp = new BuyTicketResponse(ticket.getOrigin(), ticket.getDestiny(), ticket.getAmount());
				response.add(tResp);	
			}
		} catch (Exception ex) {
			return null;
		}
		
		return response;
	}

	/**
	 * Get bought tickets
	 */
	@RequestMapping("/buyTicket")
	@ResponseBody
	public boolean buyTicket(String email, Integer amount, String from, String to, long price) {
		boolean bought = false;
		try {
			// Find an account with enough balance
			List<Account> balances = apiClient.getBalances(APPLICANT_ID);
			// Find account by email
			UserVO user = userDao.findByMail(email);
			for (AccountVO account : user.getUserAccounts()) {
				for (Account balance : balances) {
					if (!bought && balance.getId().equalsIgnoreCase(account.getAccountName())
							&& balance.getBalance().getAmount().longValue() >= price) {
						AirlineTicket ticket = apiClient.buyTicket(APPLICANT_ID, account.getAccountName(), amount, from,to);
						if (ticket.getAmount() > 0) {
							// Put ticket in database
							TicketVO ticketVO = new TicketVO(from,to,amount,user);
							ticketDao.save(ticketVO);
							bought = true;
						}
					}
				}
			}
		} catch (Exception ex) {
			return false;
		}
		return bought;
	}

	/**
	 * Get balances
	 */
	@RequestMapping("/getBalances")
	@ResponseBody
	public List<GetBalanceResponse> getBalances(String email) {
		List<GetBalanceResponse> response = new ArrayList<>();
		List<Account> balances = apiClient.getBalances(APPLICANT_ID);
		try {
			// Find account by email
			UserVO user = userDao.findByMail(email);
			for (AccountVO account : user.getUserAccounts()) {
				for (Account balance : balances) {
					if (balance.getId().equalsIgnoreCase(account.getAccountName())
							&& balance.getBalance().getAmount().longValue() > 0) {
						GetBalanceResponse balResp = new GetBalanceResponse(account.getAccountName(), balance.getBalance().getAmount().longValue(), balance.getBalance().getCurrency());
						response.add(balResp);
					}
				}

			}
		} catch (Exception ex) {
			return null;
		}
		return response;
	}

	/**
	 * Send a mail report of your tickets
	 */
	@RequestMapping("/mailMe")
	@ResponseBody
	public void sendMailReport(String email) {
		// Get all bought tickets to report
		List<BuyTicketResponse> tickets = getTicketsByEmail(email);
		StringBuilder report = new StringBuilder("Your tickets:");
		String tdStyle = "style='border: 1px solid #dddddd;text-align: left;padding: 8px;'";
		report.append("<table style='font-family: arial, sans-serif;border-collapse: collapse;width: 100%;'>"
				+ "<tr><th "+tdStyle+">From</th>"
				+ "<th "+tdStyle+">To</th>"
				+ "<th "+tdStyle+">Amount</th>");
		for(BuyTicketResponse ticket : tickets){
			report.append(" <tr>");
			report.append(" <td "+tdStyle+">"+ ticket.getFrom()+"</td>");
			report.append(" <td "+tdStyle+">"+ ticket.getTo()+"</td>");
			report.append(" <td "+tdStyle+">"+ ticket.getAmount()+"</td></tr>");
			
		}
		report.append("</table>");
		SMTPProxy.sendMail(email,report.toString());
	}

	/**
	 * Register new user
	 */
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(String email, String password) {
		boolean registered = false;
		try {
			UserVO userVO;
			// Checks if user already exists
			userVO = userDao.findByMail(email);
			if (null == userVO || userVO.getId() == 0) {
				userVO = new UserVO(email, password);
				// Create new paypallet account
				Account account = apiClient.createPayletAccount(APPLICANT_ID, Currency.USD.getCurrency());
				// Associates an account to the user
				if (null != account.getId() && !account.getId().isEmpty()) {
					// Do initial deposit
					apiClient.depositMoney(APPLICANT_ID, account.getId(), 1000, Currency.USD.getCurrency());
					AccountVO accountVO = new AccountVO(account.getId());
					// Save the account
					accountDao.save(accountVO);
					// Add the created account
					userVO.getUserAccounts().add(accountVO);
					// Save the user
					userVO = userDao.save(userVO);
					if (userVO.getId() > 0L) {
						registered = true;
					}
				}
			} else {
				// User already exists, grant access
				registered = true;
			}

		} catch (Exception ex) {
			return false;
		}
		return registered;
	}

}
