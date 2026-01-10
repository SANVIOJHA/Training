package abstraction_question3_Banking_System;

public abstract class BankAccount {
	
	double balance;
	BankAccount(double balance){
		this.balance=balance;
		
	}
	abstract void calculateInterest(double amount,int time);
	
	void deposit(double balance, double amount) {
		
	}
	void withdraw(double balance, double amount){
		
	}

}
