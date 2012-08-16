package eu.mpham.model;

import java.math.BigDecimal;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.mpham.exception.MoneyLocalesDifferException;

public class MoneyTest {

	Logger logger = LoggerFactory.getLogger(MoneyTest.class);

	@Test
	public void createAndDisplayDefaultMoney() {
		Money money = new Money();
		logger.debug(money.toString());
		Assert.assertEquals(money.toString(), "$0.00");
	}

	@Test
	public void createAndDisplayMoneyInUSD() {
		Money money = new Money(new BigDecimal("199999.99"));
		logger.debug(money.toString());
		Assert.assertEquals(money.toString(), "$199,999.99");
	}

	@Test
	public void createAndDisplayMoneyInEUR() {
		Money money = new Money(new BigDecimal("999999.99"), Locale.FRANCE);
		logger.debug(money.toString());
		Assert.assertEquals(money.toString(), "999 999,99 €");
	}

	@Test(expected = MoneyLocalesDifferException.class)
	public void tryAddingFromDifferentLocales()
			throws MoneyLocalesDifferException {
		Money m1 = new Money("150");
		Money m2 = new Money("50", Locale.FRANCE);
		m1.add(m2);
	}

	@Test
	public void addInUSD() {
		Money m1 = new Money("150.75");
		Money m2 = new Money("49.25");
		try {
			Money total = m1.add(m2);
			logger.debug(total.toString());
			Assert.assertEquals(total.toString(), "$200.00");
		} catch (MoneyLocalesDifferException ex) {
			logger.error(ex.toString());
			Assert.fail();
		}
	}

	@Test
	public void addInEUR() throws MoneyLocalesDifferException {
		Money m1 = new Money("150.75", Locale.FRANCE);
		Money m2 = new Money("49.25", Locale.FRANCE);
		Money total = m1.add(m2);
		logger.debug(total.toString());
		Assert.assertEquals(total.toString(), "200,00 €");
	}

	@Test
	public void testIfEquals() throws MoneyLocalesDifferException {
		Money m1 = new Money("50.00");
		Money m2 = new Money("50");
		Assert.assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testTaxDeduction() throws MoneyLocalesDifferException {
		Money salary = new Money("1500");
		Money afterTaxes = salary.multiply(new BigDecimal("0.84"));
		logger.debug(afterTaxes.toString());
		Assert.assertTrue(afterTaxes.equals(new Money("1260")));
	}
	
	@Test
	public void paidPerHour() throws MoneyLocalesDifferException {
		Money biWeeklyWage = new Money("1500.0");
		Money payPerHour = biWeeklyWage.divide(new BigDecimal("88.0"));
		logger.debug(payPerHour.toString());
		Assert.assertTrue(payPerHour.equals(new Money("17.05")));
	}

}
