package numberProgram;

import java.util.Scanner;
public class ProductOfAllDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		int prod=1;
		while(n!=0) {
			int d=n%10;
			prod*=d;
			n/=10;
		}
		System.out.print(prod);
sc.close();
	}

}
