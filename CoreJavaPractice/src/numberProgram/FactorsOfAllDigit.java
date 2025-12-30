package numberProgram;

import java.util.Scanner;
public class FactorsOfAllDigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		//int i=1;
		for(int i=1;i<=n;i++){
			int digit=n%10;
			if(digit%i==0) {
				System.out.println(i);
			}
			n/=n;
			
		}
//		System.out.print(prod);
sc.close();
	}

}
