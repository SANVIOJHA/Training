package conditionalstatements;

import java.util.Scanner;
public class StudentPercentageAndGrade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
//		System.out.print("enter name: ");
//		String name=sc.nextLine();
		
		int total=500;
		System.out.print("enter eng marks: ");
		double mark=sc.nextDouble();
		System.out.print("enter maths marks: ");
		double mark1=sc.nextDouble();
		System.out.print("enter hindi marks: ");
		double mark2=sc.nextDouble();
		System.out.print("enter history marks: ");
		double mark3=sc.nextDouble();
		System.out.print("enter java marks: ");
		double mark4=sc.nextDouble();
		double percentage=((mark+mark1+mark2+mark3+mark4)/total)*100;
		
		if(percentage>=90) {
			System.out.print("pass with: "+percentage+"%"+"and received grade A");
		}
		else if(percentage>=60 && percentage<90) {
			System.out.print("pass with: "+percentage+"%"+"and received grade B");
		}
		else if(percentage>=30 && percentage<60) {
			System.out.print("pass with: "+percentage+"%"+"and received grade C");
		}
		else {
			System.out.print("fail with: "+percentage+"%"+"and received grade f");
		}
		sc.close();
	}

}
