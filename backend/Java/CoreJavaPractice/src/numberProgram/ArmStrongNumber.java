package numberProgram;

import java.util.Scanner;
public class ArmStrongNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter ");
		int num=sc.nextInt();
		int ori=num;
		int count=0;
		int sum=0;
		while(num>0) {
			num/=10;
			count++;
		}
		num=ori;
		while(num>0) {
			int digit=num%10;
			sum+=Math.pow(digit, count);
			num/=10;
		}
		if(sum==ori) {
			System.out.print("is armstrong ");	
		}else {
		System.out.print("is not armstrong");
		}
		sc.close();
	}

}
