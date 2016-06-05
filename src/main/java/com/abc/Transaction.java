package com.abc;

import java.util.Date;

public class Transaction {
	public final Currency amount;

	private final Date transactionDate;

	public Transaction(Currency amount) {
		this.amount = amount;
		this.transactionDate = DateProvider.getInstance().now();
	}

	public Currency getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}
}
