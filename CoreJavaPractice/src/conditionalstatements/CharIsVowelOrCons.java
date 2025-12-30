package conditionalstatements;

import java.util.Scanner;
public class CharIsVowelOrCons {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter char: ");
		char ch=sc.next().charAt(0);
		if(ch=='a'|| ch=='e'|| ch=='i'|| ch=='o'||ch=='u' ) {
			System.out.print(ch+" is vowel");
		}else {
			System.out.print(ch+" is const");
		}
		
		sc.close();
	}
}
