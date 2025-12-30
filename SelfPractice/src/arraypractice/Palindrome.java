package arraypractice;

import java.util.Scanner;

public class Palindrome {


public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("enter n " );
	int n=sc.nextInt();
	int[] a=new int[n];
	System.out.println("first array input : ");
	for(int i=0;i<n;i++) {
		a[i]=sc.nextInt();
	}
	System.out.println("first array output : ");
	System.out.print("[");
	for(int i=0;i<n;i++) {
		System.out.print(a[i]+" ");
	}
	System.out.print("]");
	System.out.println();
	
	System.out.println("Palindrome elements:");

	for(int num:a) {
		int rev=0; int ori=num;int temp=num;
		while(temp>0) {
			int digit=temp%10;
			rev=rev*10+temp%10;
			temp/=10;
		}
		if(rev==ori) {
System.out.print(num+" is palindrome");
		}
	}
	
}

}