package polymorphism_question2_.Payment_Processing_Application;

public class UPIPayment extends Payment{
	@Override
	void pay(double amount) {
		System.out.println("UPI amount : "+amount);
	}

}
