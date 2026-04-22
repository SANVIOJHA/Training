package conditionalstatements;

import java.util.Scanner;

public class CalculatorUsingSwitch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		System.out.print("enter first number : ");
		int num1=sc.nextInt();
		System.out.print("enter second number : ");
		int num2=sc.nextInt();
		System.out.print("enter operator");
		char sym=sc.next().charAt(0);
		
		switch(sym) {
		case '+':System.out.print(num1+num2);
		break;
		
		case '-':System.out.print(num1-num2);
		break;
		case '/':System.out.print(num1/num2);
		break;
		case '*':System.out.print(num1*num2);
		break;
		case '%':System.out.print(num1%num2);
		break;
		default: System.out.print("invalid");
		
		}
		sc.close();
		
	}
}