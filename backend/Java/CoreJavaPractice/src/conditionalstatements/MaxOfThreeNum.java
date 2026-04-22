package conditionalstatements;

import java.util.Scanner;
public class MaxOfThreeNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter num1 : ");
		int a=sc.nextInt();
		System.out.print("enter num2 : ");
		int b=sc.nextInt();
		System.out.print("enter num2 : ");
		int c=sc.nextInt();
		if(a==b &&b==c) {
			System.out.print("all are equal : ");
			
		}
		else if(a>b || a>c) {
			System.out.print("a is greater");
		}
		else if(b>a ||b>c){
			System.out.print("b is greater");
		}
		else {
			System.out.print("c is greater");
		}

		sc.close();
	}

}
