package b_Money;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	// Sprawdzamy, czy metoda getAmount() poprawnie zwraca wartość kwoty.
	@Test
	public void testGetAmount() {
		 assertEquals(Double.valueOf(10000.0), SEK100.getAmount());
	}

	// Sprawdzamy, czy metoda getCurrency() zwraca poprawnie obiekt waluty.
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
	}

	// Sprawdzamy, czy metoda toString() poprawnie formatuje kwotę i walutę.
	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
	}

	// Sprawdzamy, czy metoda universalValue() poprawnie przelicza wartość na uniwersalną walutę.
	@Test
	public void testGlobalValue() {
		assertEquals(SEK.universalValue(10000), SEK100.universalValue());
	}

	 // Sprawdzamy, czy metoda equals() porównuje dwa obiekty Money i zwraca poprawny wynik.
	@Test
	public void testEqualsMoney() {
		 Money SEK100_copy = new Money(10000, SEK);
	     assertEquals(true, SEK100.equals(SEK100_copy));
	}

	// Sprawdzamy, czy metoda add() poprawnie dodaje dwie sumy pieniędzy w różnych walutach.
	@Test
	public void testAdd() {
		var sumOfValue = SEK100.add(EUR10);
		assertEquals(Optional.of(20000.0).get(), sumOfValue.getAmount());
	}

	// Sprawdzamy, czy metoda sub() poprawnie odejmuje dwie sumy pieniędzy w różnych walutach.
	@Test
	public void testSub() {
		var sub = SEK100.sub(EUR10);
		assertEquals(Optional.of(0.0).get(), sub.getAmount());
	}

	// Sprawdzamy, czy metoda isZero() zwraca poprawną wartość dla kwoty zerowej i niezerowej.
	@Test
	public void testIsZero() {
		  assertEquals(true, SEK0.isZero());
	      assertEquals(false, EUR20.isZero());
	}

	// Sprawdzamy, czy metoda negate() poprawnie neguje kwotę.
	@Test
	public void testNegate() {
		assertEquals(-10000.0, SEK100.negate().getAmount(), 0.001);
	}

	// Sprawdzamy, czy metoda compareTo() porównuje poprawnie dwie sumy pieniędzy.
	@Test
	public void testCompareTo() {
		assertEquals(0, SEK100.compareTo(SEK100));
        assertEquals(-1, SEK100.compareTo(SEK200));
	}
}
