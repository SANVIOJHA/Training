package operators;

//4.An online store gives a discount if the total purchase amount is above 2000 and the user is a premium member, 
//or if the user is not a first-time customer. Use logical and relational operators to check conditions, 
//the conditional operator to calculate the final price,
//and a compound assignment operator to reduce the bill amount.


import java.util.Scanner;

public class OnlinStore{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);

		System.out.println("total purchase : ");
		int purch=sc.nextInt();
		
		System.out.println("eUser is premium or not enter yes or no : ");
		String user=sc.next();
		
		System.out.println("number of time he/she purchased : ");
		int time=sc.nextInt();
		
	
		String action=((purch>2000 && user=="yes" )|| time!=1)?("discount is given as he/she is premium user and total amount is " + (purch-50)):("no discount given and amount is : "+purch);
		System.out.println(action);
		
sc.close();
	}

}
