package com.crossover.service;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.crossover.dto.Account;
import com.crossover.dto.AirlineOffers;
import com.crossover.dto.AirlineTicket;
import com.crossover.dto.AirlineTickets;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAPIClient extends TestCase {
	private ForestAPIClient client;
	private final String TEST_CLIENT="AlWN8EG";
	private final String TEST_ACCOUNT="account1";
	private final String TEST_CURRENCY="USD";
	
	@Before
	public void setUp() throws Exception {
		client = new ForestAPIClient();
	}

	@Test
	/***
	 * Test an account creation
	 */
	public void test1CreatePayletAccount() {
		Account account = client.createPayletAccount(TEST_CLIENT,TEST_CURRENCY);
		Logger.getAnonymousLogger().info("Account: " + account.toString());
		assertFalse(account.getId().isEmpty());
	}

	@Test
	/***
	 * Test get All offers
	 */
	public void test2GetAirlineOffers() {
		AirlineOffers response = client.getAirlineOffers(TEST_CLIENT);
		assertTrue(response.getOffers().length>0);
		Logger.getAnonymousLogger().info("Response: " + response.toString());
	}
	
	@Test
	/***
	 * Test initial deposit greater than default user balance (1000 for account1)
	 */
	public void test3InitialDeposit(){
		Account account =  client.depositMoney(TEST_CLIENT, TEST_ACCOUNT, 1000, TEST_CURRENCY);
		Logger.getAnonymousLogger().info("Account: " + account.toString());
		assertFalse(account.getId().isEmpty());
		assertTrue(account.getBalance().getAmount().longValue() >= 1000);
	}
	
	@Test
	/***
	 * Test no results for a non existing applicantID
	 */
	public void test4GetAirlineOffersByApplicantId() {
		AirlineOffers response = client.getAirlineOffers("1");
		assertNull(response.getOffers());
	}
	
	@Test
	/***
	 * Test buy two tickets from london to madrid using the initial account provided by crossover
	 */
	public void test5BuyTicket(){
		AirlineTicket response = client.buyTicket(TEST_CLIENT, TEST_ACCOUNT, new Integer(2), "London", "Madrid");//Taken from the list all tickets default result
		Logger.getAnonymousLogger().info("Ticket: " + response.toString());
		assertTrue(response.getAmount() == 2);
	}
	
	@Test
	/***
	 * Test the list of bought tickets 
	 */
	public void test6ListTickets(){
		AirlineTickets response = client.getTickets(TEST_CLIENT);
		assertNotNull(response.getTickets());
		Logger.getAnonymousLogger().info("Response: " + response.toString());
	}
	
	@Test
	/***
	 * Test the list of applicant id accounts
	 */
	public void test7ListAccounts(){
		List<Account> response = client.getBalances(TEST_CLIENT);
		assertTrue(!response.isEmpty());
		Logger.getAnonymousLogger().info("Response: " + response.toString());
	}

}
