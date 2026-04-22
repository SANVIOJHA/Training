package arraypractice;

import java.util.*;
public class SumOfAllDigits {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		long n=sc.nextLong();
		long sum=0l;
		while(n>0) {
			long digit=n%10;
			sum+=digit;
			n/=10;
			
		}
		System.out.print(sum);
		
		
		
		
		
		
		
		
		sc.close();
	}

}
