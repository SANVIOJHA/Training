package arraypractice;

import java.util.Scanner;
public class SpyNumber {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		long n=sc.nextLong();
		long sum=0l;
		long prod=1l;
		while(n>0) {
			long digit=n%10;
			sum+=digit;
			prod*=digit;
			n/=10;
			
		}
		if(sum==prod) {
		System.out.print("its spy ");
		}else {
			System.out.print("not spy ");
		}
		
		
		
		
		
		
		
		sc.close();
	}

}
