package operators;

//5.A student gets extra time in an exam if they are physically challenged or their attendance is below 60% but approved by the administration. 
//Use a conditional operator to decide the extra time and a compound assignment operator to add extra minutes to the exam duration.


import java.util.*;


public class StudentExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);

		System.out.println("student condition is physically challanged or not fill yes/no: ");
		String cond=sc.next();
		
		System.out.println("attendance : ");
		int atte=sc.nextInt();
		
		System.out.println("approved by teachers -- yes/no : ");
		String app=sc.next();
		
//		System.out.println("extra time given : ");
//		int hr=sc.nextInt();
		
	
		String action = (cond=="yes" || (atte < 60 && app=="yes"))?("student gets extra time  "):"no extra time ";
		System.out.println(action);
		
sc.close();
	}

}
