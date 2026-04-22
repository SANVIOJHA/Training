package non_primitive_upcasting;
import java.util.*;
public class Ola {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 for mini ");
		System.out.println("Enter 2 for sedan ");
		System.out.println("Enter 3 for Luxary ");
		System.out.println();
		System.out.println("enter your choice:");
		int choice=sc.nextInt();
//		Cab c=new Cab();
		Cab c=null;
		switch(choice) {
		case 1:{
			c=new Mini();
		}
		break;
		
		case 2:{
			c=new Sedan();
			}
		break;
		
		case 3:{
			c=new Luxary();
			}
		break;
		
		default:{
			System.out.println("invalid");
	
	}
		
	
	
	
	}System.out.println(c);
	c.display();
	
	

}
}
