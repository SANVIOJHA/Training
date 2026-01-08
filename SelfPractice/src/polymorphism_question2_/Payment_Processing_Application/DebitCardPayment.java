package polymorphism_question2_.Payment_Processing_Application;

public class DebitCardPayment extends Payment {
	@Override
	void pay(double amount) {
		System.out.println("Debit amount : "+amount);
	}

}
