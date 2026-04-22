package arraypractice;

import java.util.Scanner;



//25)WAJP Find Largest element in an array?

public class LargestElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		int largest=arr[0];
		System.out.println("enter arr elements: ");
		for(int i=0;i<n;i++) {
				arr[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++) {
			
			System.out.print(arr[i]+" ");	
		}
		
		System.out.println();
		
		for(int i=0;i<n-1;i++) {
			if(arr[i+1]>largest) {
				largest=arr[i+1];
			
			}
		
		}
		System.out.print(largest +" is largest");	

		
		
		sc.close();
		
	}

}
