package conditionalstatements;

import java.util.Scanner;
public class CharIsUpperLowerDigitSpecialCharNew {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter character: ");
		char ch=sc.next().charAt(0);
		
		if (ch>='A' ||ch<='Z') {
			System.out.print("uppercase");
			
		}
		else if (ch>='a' ||ch<='z') {
			System.out.print("lowercase");
			
		}
		else if (ch>='0' ||ch<='9') {
			System.out.print("digit");
			
		}
		else {
			System.out.print("special character");
		}
		sc.close();
	}

}
