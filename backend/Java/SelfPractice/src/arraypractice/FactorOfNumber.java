package arraypractice;

import java.util.Scanner;
public class FactorOfNumber {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		long n=sc.nextLong();
		
		System.out.println("factors of " +n+" are ");
		for(long i=1;i<=n;i++) {
			if(n%i==0) {
				System.out.print(i+" ");
			}
		}
		
		
		
		
		
		
		
		
		
		sc.close();
	}

}
