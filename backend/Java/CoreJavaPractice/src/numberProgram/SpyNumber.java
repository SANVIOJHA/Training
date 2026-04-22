package numberProgram;

import java.util.Scanner;
public class SpyNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		int sum=0;
		int prod=1;
		while(n!=0) {
			int d=n%10;
		
			prod*=d;
			sum+=d;
			n/=10;
		}
		
		if(sum==prod) {
			System.out.print("spy number");	
		}else {
			System.out.print("no");
		}
		
sc.close();
	}

}
