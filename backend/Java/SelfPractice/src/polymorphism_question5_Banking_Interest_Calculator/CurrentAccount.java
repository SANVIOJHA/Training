package polymorphism_question5_Banking_Interest_Calculator;

public class CurrentAccount extends BankAccount{
	void calculateInterest(double amount,double time){
		double rate=98.0;
		double interest=(rate*amount*time)/100;
		System.out.println(interest);
	}

}
