package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
        testAccount.addTimedPayment("1", 10, 0, new Money(10000000, SEK), SweBank, "Alice");
        assertTrue(testAccount.timedPaymentExists("1"));

        testAccount.removeTimedPayment("1");
        assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 1, 0, new Money(10000000, SEK), SweBank, "Alice");
		testAccount.tick(); //wystąpił, błąd w metodzie Tick() w klasie Account
		assertEquals(0, testAccount.getBalance().getAmount().intValue());
	}

	@Test
	public void testAddWithdraw() {
		testAccount.deposit(new Money(5000000, SEK));
        assertEquals(15000000, testAccount.getBalance().getAmount().intValue());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());

        testAccount.deposit(new Money(5000000, SEK));
        assertEquals(15000000, testAccount.getBalance().getAmount().intValue());
	}
}
