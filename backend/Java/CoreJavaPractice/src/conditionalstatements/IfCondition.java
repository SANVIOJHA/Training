package conditionalstatements;

import java.util.Scanner;

public class IfCondition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your age: ");
		int age=sc.nextInt();
		if(age==21) {
			System.out.println("Welcome you are eligible to vote");
		}
		
		if(age==16) {
			System.out.println("welcome thankyou");
		}

		
		
		sc.close();
	}

}
