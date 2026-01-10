package abstraction_question1_company;

public class NetBankingPayment extends Payment {
	@Override
	public void processPayment(double amount) {
		System.out.println("NetBanking : "+amount);
	}

}
