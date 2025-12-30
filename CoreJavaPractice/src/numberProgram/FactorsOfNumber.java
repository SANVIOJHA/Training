package numberProgram;

import java.util.Scanner;
public class FactorsOfNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter: ");
		int n=sc.nextInt();
		//int i=1;
		for(int i=1;i<=n;i++){
			if(n%i==0) {
				System.out.println(i);
			}
			
		}
//		System.out.print(prod);
sc.close();
	}

}
