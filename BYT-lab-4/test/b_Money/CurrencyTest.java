package b_Money;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	 // Sprawdzamy, czy metoda getName() zwraca poprawną nazwę waluty.
	@Test
	public void testGetName() {
		 assertEquals("EUR", EUR.getName());
	}
	
	// Sprawdzamy, czy metoda getRate() zwraca poprawny kurs wymiany waluty.
	@Test
	public void testGetRate() {
		 assertEquals(Double.valueOf(0.15), SEK.getRate());
	}
	
	// Sprawdzamy, czy metoda setRate() poprawnie ustawia nowy kurs wymiany waluty.
	@Test
	public void testSetRate() {
		SEK.setRate(0.25);
        assertEquals(Double.valueOf(0.25), SEK.getRate());
	}
	
	// Sprawdzamy, czy metoda universalValue() poprawnie przelicza wartość na uniwersalną walutę.
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(1500), SEK.universalValue(10000));
	}
	
	// Sprawdzamy, czy metoda valueInThisCurrency() poprawnie przelicza wartość na inną walutę.
	@Test
	public void testValueInThisCurrency() {
		var amount = EUR.valueInThisCurrency(2000, SEK);
		assertEquals(Optional.of(200).get(), amount);

	}

}
