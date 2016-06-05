package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public Currency totalInterestEarned() {
		Currency total = new Currency(0d);
		for (Account a : accounts)
			total.plus(a.interestEarned());
		return total;
	}

	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		Currency total = new Currency(0d);
		for (Account a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total.plus(a.sumTransactions());
		}
		statement += "\nTotal In All Accounts " + toDollars(total.getMoney());
		return statement;
	}

	private String statementForAccount(Account a) {
		String s = "";

		// Translate to pretty account type
		switch (a.getAccountType()) {
		case CHECKING:
			s += "Checking Account\n";
			break;
		case SAVINGS:
			s += "Savings Account\n";
			break;
		case MAXI_SAVINGS:
			s += "Maxi Savings Account\n";
			break;
		}

		// Now total up all the transactions
		Currency total = new Currency(0d);
		for (Transaction t : a.transactions) {
			s += "  " + (t.amount.getMoney() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount.getMoney())
					+ "\n";
			total.plus(t.amount);
		}
		s += "Total " + toDollars(total.getMoney());
		return s;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}
}
