package conditionalstatements;

import java.util.Scanner;

public class UserInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("name: ");
		String name=sc.nextLine();
		System.out.println("age: ");
		int age=sc.nextInt();
		System.out.println("marks: ");
		double marks=sc.nextDouble();
//		System.out.println("first: ");
//		char ch=sc.next().charAt(0);
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(marks);
//		System.out.println(firstLetter);
		System.out.println("firstLetter of name is : "+name.charAt(0));
		System.out.println("lastLetter of name is : "+name.charAt(name.length()-1));
//		System.out.println(ch);
		sc.close();
	}

}
