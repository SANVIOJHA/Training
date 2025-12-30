package operators;

//9.An electricity board checks if the units consumed are greater than 300 or the user is a commercial customer. 
//Use logical and relational operators to decide the billing rate, the conditional operator to calculate the bill, 
//and a compound assignment operator to add tax to the total amount.



import java.util.*;


public class Electricity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		
//		System.out.println("username : ");
//		String name=sc.next();
		
		System.out.println("unit : ");
		int unit=sc.nextInt();
		
		int bill =900;
		
		System.out.println("user is commercial or not -- yes/no  : ");
		String user=sc.next();
		
		
		String action = (unit>300 || user=="yes")?"total bill = "+(bill*unit):("not comercial user "+(bill*unit+10000));
		System.out.println(action);
		
		sc.close();
	}

}