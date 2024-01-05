package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
        assertEquals("Nordea", Nordea.getName());
        assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		//błąd w metodzie openAccount()
		SweBank.openAccount("Roksana");
		SweBank.deposit("Roksana", new Money(12340, SEK));
		assertEquals(12340, SweBank.getBalance("Roksana").intValue());
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		 SweBank.deposit("Ulrika", new Money(100, SEK)); //błąd w metodzie Deposit()
	     assertEquals(100, SweBank.getBalance("Ulrika").intValue()); 
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		SweBank.withdraw("Bob", new Money(1000, SEK));//błąd w metodzie Withdraw()
		assertEquals(0, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100000000, SEK));
		assertEquals(100000000, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(50000000, SEK));
		SweBank.transfer("Bob", Nordea, "Bob", new Money(30000000, SEK));

		assertEquals(20000000, SweBank.getBalance("Bob").intValue());
		assertEquals(30000000, Nordea.getBalance("Bob").intValue());

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000000, SEK));
		SweBank.addTimedPayment("Ulrika", "1", 10, 0, new Money(1000000, SEK), SweBank, "Bob");
		SweBank.tick();
		assertEquals(0, SweBank.getBalance("Ulrika").intValue());
	}
}
