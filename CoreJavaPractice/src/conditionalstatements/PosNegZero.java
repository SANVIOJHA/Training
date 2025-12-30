package conditionalstatements;

import java.util.Scanner;
public class PosNegZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter num : ");
		int num=sc.nextInt();
		if(num==0) {
			System.out.print(num+" is zero");
		}
		else if(num<0) {
			System.out.print(num+" is negative");
		}
		else {
			System.out.print(num+" is positive");
		}
		
		sc.close();

	}

}
