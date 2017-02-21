package com.crossover.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crossover.dto.Account;
import com.crossover.dto.AirlineOffer;
import com.crossover.dto.AirlineOffers;
import com.crossover.dto.AirlineRoute;
import com.crossover.dto.AirlineTicket;
import com.crossover.dto.AirlineTickets;
import com.crossover.dto.BuyTicketRequest;
import com.crossover.dto.CreateAccountRequest;
import com.crossover.dto.DepositWithDrawRequest;
import com.crossover.dto.MonetaryAmount;

@Component
public class ForestAPIClient implements ResponseErrorHandler{
	public static final String URL = "https://api-forest.crossover.com";
	public static final String GET_OFFERS_ACTION = "/{applicantId}/gammaairlines/offers";
	public static final String DEPOSIT_MONEY_ACTION = "/{applicantId}/paypallets/account/deposit";
	public static final String CREATE_ACCOUNT_ACTION = "/{applicantId}/paypallets/account";
	public static final String BUY_TICKET_ACTION = "/{applicantId}/gammaairlines/tickets/buy";
	public static final String GET_TICKETS_ACTION = "/{applicantId}/gammaairlines/tickets";
	public static final String GET_ACCOUNTS_ACTION = "/{applicantId}/paypallets/accounts";

	// -- Params
	public static final String PARAM_APPLICANT_ID = "applicantId";

	/***
	 * Client for deposit money operation
	 * @param applicantId
	 * @param accountId
	 * @param amount
	 * @param currency
	 * @return
	 */
	public Account depositMoney(String applicantId, String accountId, Number amount, String currency){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(this);
		Account account = new Account();
		DepositWithDrawRequest request = new DepositWithDrawRequest(accountId, new MonetaryAmount(amount, currency));
		// URI (URL) parameters
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put(PARAM_APPLICANT_ID, applicantId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL + DEPOSIT_MONEY_ACTION);
		try {
			ResponseEntity<Account> response = restTemplate.postForEntity(builder.buildAndExpand(uriParams).toUri(), request, Account.class);
			account = response.getBody();
		} catch (HttpClientErrorException e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		} 
		return account;
	}
	
	/***
	 * Client for the buy ticket operation
	 * @param applicantId
	 * @param accountId
	 * @param amount
	 * @param from
	 * @param to
	 * @return
	 */
	public AirlineTicket buyTicket(String applicantId, String accountId, Integer amount, String from, String to){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(this);
		AirlineTicket ticket = new AirlineTicket();
		BuyTicketRequest request = new BuyTicketRequest(accountId, amount, new AirlineRoute(from, to));
		// URI (URL) parameters
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put(PARAM_APPLICANT_ID, applicantId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL + BUY_TICKET_ACTION);
		try {
			ResponseEntity<AirlineTicket> response = restTemplate.postForEntity(builder.buildAndExpand(uriParams).toUri(), request, AirlineTicket.class);
			ticket = response.getBody();
		} catch (HttpClientErrorException e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		} 
		return ticket;
	}
	/***
	 * Client for create account operation
	 * @param applicantId
	 * @param currency
	 * @return
	 */
	public Account createPayletAccount(String applicantId, String currency) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(this);
		Account account = new Account();
		CreateAccountRequest request = new CreateAccountRequest(currency);
		// URI (URL) parameters
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put(PARAM_APPLICANT_ID, applicantId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL + CREATE_ACCOUNT_ACTION);
		try {
			ResponseEntity<Account> response = restTemplate.postForEntity(builder.buildAndExpand(uriParams).toUri(), request, Account.class);
			account = response.getBody();
		} catch (HttpClientErrorException e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		} 
		return account;
	}

	/***
	 * Client for Get Offers operation
	 * 
	 * @param applicantId
	 * @return
	 */
	public AirlineOffers getAirlineOffers(String applicantId) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(this);
		ResponseEntity<AirlineOffer[]> response;
		AirlineOffers offers = new AirlineOffers();
		// URI (URL) parameters
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put(PARAM_APPLICANT_ID, applicantId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL + GET_OFFERS_ACTION);
		try {
			response = restTemplate.getForEntity(builder.buildAndExpand(uriParams).toUri(), AirlineOffer[].class);
			offers.setOffers(response.getBody());
		} catch (HttpClientErrorException e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
		return offers;
	}
	
	/**
	 * Lists all accounts
	 * @param applicantId
	 * @return
	 */
	public List<Account> getBalances(String applicantId) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(this);
		ResponseEntity<Account[]> response;
		List<Account> accounts = new ArrayList<>();
		// URI (URL) parameters
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put(PARAM_APPLICANT_ID, applicantId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL + GET_ACCOUNTS_ACTION);
		try {
			response = restTemplate.getForEntity(builder.buildAndExpand(uriParams).toUri(), Account[].class);
			accounts = Arrays.asList(response.getBody());
		} catch (HttpClientErrorException e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
		return accounts;
	}
	
	/***
	 * Client for the list tickets operation
	 * @param applicantId
	 * @return
	 */
	public AirlineTickets getTickets(String applicantId) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(this);
		ResponseEntity<AirlineTicket[]> response;
		AirlineTickets tickets = new AirlineTickets();
		// URI (URL) parameters
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put(PARAM_APPLICANT_ID, applicantId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL + GET_TICKETS_ACTION);
		try {
			response = restTemplate.getForEntity(builder.buildAndExpand(uriParams).toUri(), AirlineTicket[].class);
			tickets.setTickets(response.getBody());
		} catch (HttpClientErrorException e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
		return tickets;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException{
		// Extracts the message error
		String responseBody = IOUtils.toString(response.getBody());
		if(null!= responseBody && !responseBody.isEmpty() && responseBody.contains(":")){
			responseBody = responseBody.split(":")[1];
			responseBody = responseBody.replaceAll("\"|\\}", "");
			Logger.getLogger(getClass()).error("Message : "+ responseBody);
		}
		Logger.getLogger(getClass()).error("Response error: "+response.getStatusCode() + " "+ response.getStatusText());
		// TODO call back on exception to inform an error
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode() != HttpStatus.OK;
	}
}
