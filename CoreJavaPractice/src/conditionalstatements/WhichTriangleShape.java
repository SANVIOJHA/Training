package conditionalstatements;

import java.util.Scanner;
public class WhichTriangleShape {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter a : ");
		int a=sc.nextInt();
		System.out.print("enter b : ");
		int b=sc.nextInt();
		System.out.print("enter c : ");
		int c=sc.nextInt();
		
		if(a==b &&b==c && a==c) {
			System.out.print("triangle is equilateral: ");
		}
		else if(a==b || b==c ||a==c) {
			System.out.print("triangle is isosceles: ");
		}
		else {
			System.out.print("triangle is scalene: ");
		}
		sc.close();

	}

}
