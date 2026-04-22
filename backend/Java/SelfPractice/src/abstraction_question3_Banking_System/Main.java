package abstraction_question3_Banking_System;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("enter balance : ");
		double balance=sc.nextDouble();
		
		System.out.println("enter  amount: ");
		double amount=sc.nextDouble();
		
		System.out.println("enter time: ");
		int time=sc.nextInt();
		
//		BankAccount b =new SavingsAccount(balance);
		BankAccount b;
		b=new SavingsAccount(balance);
		b.calculateInterest(amount,time);
		//b.calculateInterest(amount, time);
		
	}
}
