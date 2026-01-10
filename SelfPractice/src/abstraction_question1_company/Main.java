package abstraction_question1_company;

public class Main {
	public static void main(String args[]) {
		Payment p;
		p=new CreditCardPayment();
		p.printReceipt();
		p.processPayment(4500);
		System.out.println();
		p=new UPIPayment();
		p.printReceipt();
		p.processPayment(7800);
		System.out.println();
		p=new NetBankingPayment();
		p.printReceipt();
		p.processPayment(20);
		System.out.println();
	}
}
