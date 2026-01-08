package polymorphism_question5_Banking_Interest_Calculator;

public class SavingsAccount extends BankAccount{
	
	void calculateInterest(double amount,double time){
		double rate=9.0;
		double interest=(rate*amount*time)/100;
		System.out.println(interest);
	}

}
