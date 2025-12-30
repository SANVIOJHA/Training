package arraypractice;

import java.util.Scanner;

public class MergeArray {


public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("enter n " );
	int n=sc.nextInt();
	System.out.print("enter m " );
	int m=sc.nextInt();
	int[] a=new int[n];
	int[] b=new int[m];
	int[] merge =new int[n+m];
	
	System.out.println("first array input : ");
	for(int i=0;i<n;i++) {
		a[i]=sc.nextInt();
	}

	System.out.println("second array: input ");
	
	
	for(int i=0;i<n;i++) {
		b[i]=sc.nextInt();
	}
	
	for(int i=0;i<n;i++) {
		merge[i]=a[i];
	}
	for(int i=0;i<m;i++) {
		merge[n+i]=b[i];
	}
	System.out.println("merged array is  ");
	for(int i=0;i<merge.length;i++) {
		System.out.print(merge[i]);
		
	}
	
}

}