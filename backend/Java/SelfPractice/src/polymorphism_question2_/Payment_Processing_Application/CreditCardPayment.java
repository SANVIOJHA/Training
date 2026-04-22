package polymorphism_question2_.Payment_Processing_Application;

public class CreditCardPayment extends Payment {
	
	@Override
	void pay(double amount) {
		System.out.println("Credit amount "+amount);
	}

}
