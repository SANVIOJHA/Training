package conditionalstatements;

import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		///if there is no break then all statements will be executed///
		/// statements will be executed from where the cases matched before case matched statements will not be executed
		//break ---- control transfer statement
	
		Scanner sc=new Scanner(System.in);
		System.out.print("enter value: ");
		int a=sc.nextInt();
		switch(a) {
		case 1:System.out.println("hi");
		break;
		case 2:System.out.println("hello");
		break;
		case 3:System.out.println("bye");
		break;
		
		default:System.out.println("its default");
		break;
		
		
		
		
		}
		sc.close();
	}

}
