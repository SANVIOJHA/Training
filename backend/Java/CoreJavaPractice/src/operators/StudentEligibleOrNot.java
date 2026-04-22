package operators;

//1. A program checks a student’s marks.
//If the marks are greater than or equal to 40 and attendance is above 75%, the student passes; 
//otherwise, the student fails.Use relational and logical operators to check eligibility, 
//the conditional operator to decide pass or fail, and a compound assignment operator to add 5 bonus marks if the student passes.

import java.util.*;

public class StudentEligibleOrNot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		
		System.out.println("marks: ");
		int mark=in.nextInt();
		
		System.out.println("attendance: ");
		int att=in.nextInt();
		
	
		String passOrFail=(mark>=40 && att>=75)?("Pass"+(mark+5)):"Fail";
		System.out.println(passOrFail);
		
		in.close();

	}

}
