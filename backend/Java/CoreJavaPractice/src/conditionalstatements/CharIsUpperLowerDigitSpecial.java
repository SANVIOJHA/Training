package conditionalstatements;

import java.util.Scanner;
public class CharIsUpperLowerDigitSpecial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter character: ");
		char ch=sc.next().charAt(0);
		if (Character.isUpperCase(ch)) {
			System.out.print("uppercase");
			
		}
		else if (Character.isLowerCase(ch)) {
			System.out.print("lowercase");
			
		}
		else if (Character.isDigit(ch)) {
			System.out.print("digit");
			
		}
		else {
			System.out.print("special character");
		}
		sc.close();
	}

}
