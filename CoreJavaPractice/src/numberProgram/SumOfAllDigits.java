package numberProgram;

import java.util.Scanner;
public class SumOfAllDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
//		System.out.print(n);
		int sum=0;
		while(n!=0) {
			int d=n%10;
			sum+=d;
			n/=10;
		}
		System.out.print(sum);
		sc.close();
	}

}
