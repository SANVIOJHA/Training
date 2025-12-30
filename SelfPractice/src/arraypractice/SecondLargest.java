package arraypractice;

import java.util.Scanner;


public class SecondLargest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		int largest=Integer.MIN_VALUE;
		int secLargest=Integer.MIN_VALUE;
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
				secLargest=largest;
				largest=arr[i+1];
			
			}else if(arr[i+1]>secLargest && secLargest!=largest) {
				secLargest=arr[i+1];
			}
		
		}
		System.out.print(secLargest +" is second largest");	

		
		
		sc.close();
		
	}

}
