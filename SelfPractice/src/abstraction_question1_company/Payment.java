package abstraction_question1_company;

public abstract class Payment {
	public void printReceipt(){
		System.out.println("concrete method");
	}
	
	public abstract void processPayment(double amount);

}
