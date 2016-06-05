package com.abc;

import java.math.BigDecimal;

public class Currency {
	private double money;
	private long dollar;
	private long cents;

	public Currency(double money) {
		this.money = money;
		calculateMoney(money);
	}

	private void calculateMoney(double money) {
		this.dollar = (long) money;
		this.cents = (long) Math.round((money - (long) money) * 100);
	}

	private long getDollar(double money) {
		return (long) money;
	}

	private long getCents(double money) {
		return (long) (long) Math.round((money - (long) money) * 100);
	}

	public double getMoney() {
		return money;
	}

	public Currency plus(Currency value) {
		return plus(value.getMoney());
	}

	public Currency minus(Currency value) {
		return minus(value.getMoney());
	}

	public Currency plus(double value) {
		this.money = (this.dollar + getDollar(value)) + ((double) (getCents(value) + this.cents) / 100);
		calculateMoney(this.money);
		return this;
	}

	public Currency minus(double value) {
		this.money = (this.dollar - getDollar(value)) - ((double) (getCents(value) + this.cents) / 100);
		calculateMoney(this.money);
		return this;
	}

	public Currency multiply(double value) {
		this.money = BigDecimal.valueOf(money).multiply(BigDecimal.valueOf(value)).doubleValue();
		calculateMoney(money);
		return this;
	}
}
