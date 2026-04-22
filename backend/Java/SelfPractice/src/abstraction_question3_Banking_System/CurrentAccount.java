package abstraction_question3_Banking_System;

public class CurrentAccount extends BankAccount {
	
	

   public  CurrentAccount(double balance) {
        super(balance);
    }
    
	@Override
	void calculateInterest(double amount,int time) {
		double rate=7.5;
		double interest=(rate*amount*time)/100;
		System.out.println("Interest of Savings Account is : "+ interest);
	}
	@Override
	void deposit(double balance,double amount) {
		balance=balance+amount;
		System.out.println("after amount deposit balance is : "+balance);
	}
	@Override
	void withdraw(double balance,double amount){
		balance=balance-amount;
		System.out.println("after amount withdrawn balance is  "+balance);
	}
}