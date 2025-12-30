package numberProgram;

import java.util.Scanner;

public class CountDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter ");
		
		int n=sc.nextInt();
		int count=0;
		while(n!=0){
//			int d=n%10;
			n=n/10;
			count++;
			
			
			
		}
		System.out.print(count);
		sc.close();
		
	}

}
