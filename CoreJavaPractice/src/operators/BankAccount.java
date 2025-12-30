package operators;

import java.util.Scanner;

public class BankAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		System.out.println("balance: ");
		int balance=sc.nextInt();
		
		System.out.println("amount: ");
		int amt=sc.nextInt();
		
	
		String action=(balance>0 )?("withdraw and after withdrawal total "+(balance-amt)):"deny";
		System.out.println(action);
	
		sc.close();

	}

}