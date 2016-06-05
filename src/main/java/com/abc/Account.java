package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private final AccountType accountType;
	public List<Transaction> transactions;

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(new Currency(amount)));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			Transaction transaction = new Transaction(new Currency(-amount));
			transactions.add(transaction);
		}
	}

	public Currency interestEarned() {
		// long amount =
		Currency amount = sumTransactions();
		switch (accountType) {
		case SAVINGS:
			if (amount.getMoney() <= 1000)
				return amount.multiply(0.001);
			else {
				Currency first1000 = new Currency(1000d).multiply(0.001);
				return (amount.minus(1000).multiply(0.002).plus(first1000));
			}
		case MAXI_SAVINGS:
			if (amount.getMoney() <= 1000)
				return amount.multiply(0.02);
			if (amount.getMoney() <= 2000) {
				Currency first1000 = new Currency(1000d).multiply(0.02);
				return (amount.minus(1000).multiply(0.05)).plus(first1000);
			}

			Currency first1000 = new Currency(1000d).multiply(0.02);
			Currency first2000 = new Currency(1000d).multiply(0.05);

			return amount.minus(2000).multiply(0.1).plus(first1000).plus(first2000);
		default:
			return amount.multiply(0.001);
		}
	}

	public Currency sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private Currency checkIfTransactionsExist(boolean checkAll) {
		Currency currency = new Currency(0d);
		for (Transaction t : transactions) {
			currency.plus(t.getAmount());
		}
		return currency;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void transfer(Account account, double money) {
		account.deposit(money);
		this.withdraw(money);
	}
	
}
