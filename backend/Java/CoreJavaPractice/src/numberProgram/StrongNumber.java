package numberProgram;

import java.util.Scanner;
public class StrongNumber {
//sunny number=>  sum of each factorial ===original number
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		//int i=1;
		int sum=0;
		int origNum=n;
		if(n==0) {
			System.out.print("factorial of zero is 1");
		}else {
		
		int fact=1;
		for(int i=1;i<=n;i++){
			fact*=i;
			
		}
		sum+=fact;
		}
		if(sum==origNum) {
			System.out.print("equal");
			}else {
				System.out.println("not equal");
			}
sc.close();
	}

}
