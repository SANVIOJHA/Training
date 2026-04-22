package conditionalstatements;

import java.util.Scanner;

public class EvenOrOddWithoutModulo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter num : ");
		int num=sc.nextInt();
		if((num & 1)==0) {
			System.out.print("even");
			
		}else {
			System.out.print("odd");
		}

		
		sc.close();
		
	}

}
