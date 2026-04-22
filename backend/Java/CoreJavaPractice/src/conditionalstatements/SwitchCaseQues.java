package conditionalstatements;

import java.util.Scanner;
public class SwitchCaseQues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter character : ");
		char ch=sc.next().charAt(0);
		switch(ch) {
		case 'a':System.out.println("apple");
		break;
		case 'b':System.out.println("ball");
		break;
		case 'c':System.out.println("cat");
		break;
		case 'd':System.out.println("dog");
		break;
		case 'e':System.out.println("eagle");
		break;
		default:System.out.print("enter a to e only ");
		}
		sc.close();
	}

}
