package arraypractice;

import java.util.Scanner;

public class ReverseEachElement {


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
	
	

	for(int num:a) {
		int rev=0; int t=num;
		while(t>0) {
			
			//rev=rev*10+temp%10;///instead of num if we use temp then this will reverse each digits 
			rev=rev*10+t%10;
			t/=10;
		}
		
System.out.print(rev+" ");
		
	}
	
}

}