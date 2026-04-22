package polymorphism_question2_.Payment_Processing_Application;

public class Main {
	public static void main(String[] args) {
		//Payment a=new Payment();
		
	Payment p;/// single reference 
	
	p=new CreditCardPayment();
	p.pay(45582);
	p=new DebitCardPayment();
	p.pay(78594);
	p=new UPIPayment();
	p.pay(12453);
	
	
}
}
