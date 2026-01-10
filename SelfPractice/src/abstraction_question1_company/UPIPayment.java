package abstraction_question1_company;

public class UPIPayment extends Payment {
	@Override
	public void processPayment(double amount) {
		System.out.println("UPI  : "+amount);
	}

}
