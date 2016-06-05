package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void customerSummary() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new Account(AccountType.CHECKING));
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
	}

	@Test
	public void checkingAccount() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.CHECKING);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(5000.0);

		assertEquals(5.0, bank.totalInterestPaid().getMoney(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(1500.0);

		assertEquals(2.0, bank.totalInterestPaid().getMoney(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0);

		assertEquals(170.0, bank.totalInterestPaid().getMoney(), DOUBLE_DELTA);
	}

	@Test
	public void transfer() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
		checkingAccount.deposit(3000.0);

		Account checkingAccount1 = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Snehal").openAccount(checkingAccount));
		checkingAccount1.deposit(3000.0);

		checkingAccount.transfer(checkingAccount1, 200);
		assertEquals(3200.0, checkingAccount1.sumTransactions().getMoney()  ,DOUBLE_DELTA);
		
		assertEquals(2800.0, checkingAccount.sumTransactions().getMoney()  ,DOUBLE_DELTA);

	}

}
