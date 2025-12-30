package operators;

//6.A traffic system checks if a vehicle’s speed is greater than the speed limit and the driver is not an emergency service provider. 
//Use a conditional operator to decide whether a fine should be applied 
//and a compound assignment operator to add the fine amount to the total penalty.



import java.util.Scanner;


public class Traffic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);

		
		System.out.println("speed : ");
		int speed=sc.nextInt();
		
		System.out.println("driver is emergency service provider -- yes/no : ");
		String dri=sc.next();
		
		
		
	
		String action = (speed>=100 &&  dri!="yes")?("fine of  "+1000+" rupees will be applied"):"no fine ";
		System.out.println(action);
		
sc.close();
	}

}
