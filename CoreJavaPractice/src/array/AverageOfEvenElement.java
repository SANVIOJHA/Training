package array;

import java.util.Scanner;



//12)WAJP Find Average of even elements an array?

public class AverageOfEvenElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		double sum=0d;
		double avg = 0d;
		System.out.println("enter arr elements: ");
		for(int i=0;i<n;i++) {
				arr[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++) {
			
			System.out.print(arr[i]+" ");	
		}
		
		System.out.println();
		
		
		for(int i=0;i<n;i++) {
			if(arr[i]%2==0) {
			sum+=arr[i];
			
			}
			avg=sum/(n);
		}
		System.out.print(avg);


		
		sc.close();
		
		
	}

}
