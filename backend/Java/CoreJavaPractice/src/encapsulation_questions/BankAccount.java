package encapsulation_questions;
///1. Bank Account
//Write a Java program to create a BankAccount class with:
//accountNumber and balance as private
//methods: deposit(), withdraw(), and getBalance()
//ensure balance cannot be directly modified ///
///
public class BankAccount {
	private long accountNumber;
	private double balance;
	
	public BankAccount(long accountNumber,double balance) {
		this.accountNumber=accountNumber;
		this.balance=balance;
	}

//	public void setAccountNumber(long accountNumber) {
//		this.accountNumber=accountNumber;
//	}
	
	void diposit(double amount) {
		if(amount>0) {
		balance= balance+amount;
		}
	}
	void withdraw(double amount) {
		if(amount<0) {
			System.out.println("insufficient");
		}else {
		balance= balance-amount;
		}
	}
	
	
	public long getAccountNumber() {
	return accountNumber;
}
	public double getBalance() {
	return balance;
}
	void display() {
	System.out.println("account number : "+accountNumber);
	System.out.println("balance  : "+balance);
	}
	
	
	
}
