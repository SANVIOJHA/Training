package operators;

//3.An employee is eligible for a salary increment if their performance rating is greater than 4 or they have more than 5 years of experience, 
//but they must not be under disciplinary action.
//Use a conditional operator to decide eligibility and a compound assignment operator to increase the salary.


import java.util.Scanner;

public class EmpSalary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);

		System.out.println("performance rating : ");
		int perRating=sc.nextInt();
		
		
		System.out.println("experience : ");
		int exp=sc.nextInt();
		
	
		String action=(perRating>4 && exp>5)?("salary increament  "):"disciplinary action";
		System.out.println(action);
		
sc.close();
	}

}
