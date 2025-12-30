package array;

import java.util.Scanner;



//11)WAJP Find Average of an array?

public class AverageOfElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		int sum=0;
		int avg = 0;
		System.out.println("enter arr elements: ");
		for(int i=0;i<n;i++) {
				arr[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++) {
			
			System.out.print(arr[i]+" ");	
		}
		
		System.out.println();
		
		
		for(int i=0;i<n;i++) {
			sum+=arr[i];
			avg=sum/(n);
			
		}
		System.out.print(avg);


		
		sc.close();
		
		
	}

}
