package encapsulation_questions;
import java.util.*;
public class Main {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter account number: ");
		long accountNumber=sc.nextLong();
		System.out.println("enter balance: ");
		double balance=sc.nextDouble();
		System.out.println("enter amount: ");
		double amount=sc.nextDouble();
		
//		System.out.println("enter withdrawal amount: ");
//		double withdraw=sc.nextInt();
//		
		BankAccount b=new BankAccount(accountNumber,balance);
		b.diposit(amount);
		b.getAccountNumber();
		
		
	}
}
