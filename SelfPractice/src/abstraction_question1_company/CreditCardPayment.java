package abstraction_question1_company;

public class CreditCardPayment extends Payment {
	@Override
	public void processPayment(double amount) {
		System.out.println("Credit card : "+amount);
	}

}
