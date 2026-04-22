package conditionalstatements;

import java.util.Scanner;

public class ElseIfCondition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a: ");
		int a=sc.nextInt();
		System.out.println("enter b: ");
		int b=sc.nextInt();
		
		if(a==b) {
			System.out.println("both are same");
		}
		
		else if(a>b){
			System.out.println(a+" is greater");
		}
		else {
			System.out.println(b+" is greater");
		}

		
		
		sc.close();	
	}
	

}
