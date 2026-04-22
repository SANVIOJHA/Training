package arraypractice;

import java.util.Scanner;
public class FactorOfAllDigits {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		long n=sc.nextLong();
		
		System.out.println("factors of " +n+" are ");
		long i=1;
		while(n>0) {
			long digit=n%10;
			if(digit%i==0) {
				System.out.println("factor of "+digit+" is "+i);
				n/=10;
				
			}
			i++;
			
		}
		
		
		
		
		
		
		
		
		
		sc.close();
	}

}
