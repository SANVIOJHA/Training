package polymorphism_question5_Banking_Interest_Calculator;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter account type: ");
		System.out.println("1 for Saving account type: ");
		System.out.println("2 for fixed deposit Saving account type: ");
		System.out.println("3 for current  account type: ");
		
		int whichAccount=sc.nextInt();
		
		double amount=4986.458d;
		
		double time=9;
		
		BankAccount b;
		switch(whichAccount) {
		case 1:{
			System.out.println("its saving account");
			b=new SavingsAccount();
			//b.calculateInterest();
		}
		break;
		
		case 2:{
			System.out.println("its fixed account");
			b=new FixedDepositAccount();
			//b.calculateInterest();
		}
		break;
		
		case 3:{
			System.out.println("its current account");
			b=new CurrentAccount();
			//b.calculateInterest();
		}
		break;
		
		default: System.out.println("enter valid input 1  2  3");
		break;
	
		
		}
		b.calculateInterest(amount,time);
	}

}
