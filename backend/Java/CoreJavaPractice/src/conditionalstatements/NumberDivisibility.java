package conditionalstatements;

import java.util.Scanner;
public class NumberDivisibility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter num : ");
		int num=sc.nextInt();
		if(num%5==0 &&num%11==0) {
			System.out.print(num+" is divisible by both 5 and 11");
		}
		else {
			System.out.print(num+" is not divisible by both 5 and 11");
		}
sc.close();
	}

}
