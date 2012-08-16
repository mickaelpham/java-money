package eu.mpham.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import eu.mpham.exception.MoneyLocalesDifferException;

public class Money {
	
	private static final int SCALE = 2;

	private BigDecimal amount;
	private Locale locale;

	public Money() {
		this.amount = new BigDecimal("0");
		this.locale = Locale.US;
	}

	public Money(BigDecimal amount) {
		this.amount = amount;
		this.locale = Locale.US;
	}

	public Money(String amount) {
		this.amount = new BigDecimal(amount);
		this.locale = Locale.US;
	}

	public Money(BigDecimal amount, Locale locale) {
		this.amount = amount;
		this.locale = locale;
	}

	public Money(String amount, Locale locale) {
		this.amount = new BigDecimal(amount);
		this.locale = locale;
	}

	public Money add(Money other) throws MoneyLocalesDifferException {
		if (!this.locale.equals(other.getLocale()))
			throw new MoneyLocalesDifferException();
		BigDecimal amount = this.amount.add(other.getAmount());
		return new Money(amount, this.locale);
	}
	
	public Money substract(Money other) throws MoneyLocalesDifferException {
		if (!this.locale.equals(other.getLocale()))
			throw new MoneyLocalesDifferException();
		BigDecimal amount = this.amount.subtract(other.getAmount());
		return new Money(amount, this.locale);
	}

	public boolean equals(Money other) throws MoneyLocalesDifferException {
		if (!this.locale.equals(other.getLocale()))
			throw new MoneyLocalesDifferException();
		return amount.compareTo(other.getAmount()) == 0;
	}
	
	public int compareTo(Money other) throws MoneyLocalesDifferException {
		if (!this.locale.equals(other.getLocale()))
			throw new MoneyLocalesDifferException();
		return amount.compareTo(other.getAmount());
	}
	
	public Money divide(BigDecimal factor) {
		BigDecimal total = this.amount.divide(factor, SCALE, RoundingMode.HALF_UP);
		return new Money(total, this.locale);
	}
	
	public Money multiply(BigDecimal factor) {
		BigDecimal total = this.amount.multiply(factor);
		return new Money(total, this.locale);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(amount.doubleValue());
	}

}
