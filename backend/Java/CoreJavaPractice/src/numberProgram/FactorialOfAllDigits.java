package numberProgram;

import java.util.Scanner;
public class FactorialOfAllDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		//int i=1;
		int ori=n;
		while(ori>0) {
			
			int digit=ori%10;
			int fact=1;
			for(int k=1;k<=digit;k++) {
				fact*=k;
			}
			System.out.println(fact);
			ori/=10;
			
			
		}
		
		
		
		
		
		
sc.close();
	}

}
