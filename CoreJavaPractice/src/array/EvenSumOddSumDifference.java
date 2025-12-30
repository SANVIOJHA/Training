package array;

import java.util.Scanner;



//13)WAJP Find difference of Even sum and Odd sum?

public class EvenSumOddSumDifference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		int eveSum=0;
		int oddSum=0;
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
				eveSum+=arr[i];	
			}else if (arr[i]%2!=0){
				oddSum+=arr[i];	
			}
			
		}
		System.out.print(eveSum-oddSum);


		sc.close();
		
		
		
	}

}
