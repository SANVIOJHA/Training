package arraypractice;

import java.util.Scanner;
public class ProductOfAllDigits {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		long n=sc.nextLong();
		long prod=1l;
		while(n>0) {
			long digit=n%10;
			prod*=digit;
			n/=10;
			
		}
		System.out.print(prod);
		
		
		
		
		
		
		
		
		sc.close();
	}

}


