package numberProgram;

import java.util.Scanner;
public class FactorialNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		//int i=1;
		if(n==0) {
			System.out.print("factorial of zero is 1");
		}else {
		
		int fact=1;
		for(int i=1;i<=n;i++){
			fact*=i;
			
		}
		System.out.print(fact);
		}
sc.close();
	}

}
