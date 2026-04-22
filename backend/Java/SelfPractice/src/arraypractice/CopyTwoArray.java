package arraypractice;

import java.util.Scanner;

public class CopyTwoArray {


public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("enter n " );
	int n=sc.nextInt();
	System.out.print("enter m " );
	int m=sc.nextInt();
	int[] a=new int[n];
	int[] b=new int[m];
	
	
	System.out.print("first array input : ");
	for(int i=0;i<n;i++) {
		a[i]=sc.nextInt();
	}

	System.out.print("second array: input ");
	
	for(int i=0;i<n;i++) {
		b[i]=a[i];
	}
	
	for(int i=0;i<n;i++) {
//		System.out.print(a[i]);
//		System.out.println();
		System.out.print(b[i]);
	}
}

}