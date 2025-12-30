package array;

import java.util.Scanner;


//1)WAJP Print element in array which is divisible by 5 ?
		
public class DivisibleByFive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		System.out.println("enter arr elements: ");
		for(int i=0;i<n;i++) {
			
			arr[i]=sc.nextInt();
			
		}
		for(int i=0;i<n;i++) {
			
//			System.out.print(arr[i]+" ");
			if(arr[i]%5==0) {
				System.out.println(arr[i]+" is divisible");
			}
		}
		
		
		System.out.println();
		
		
		sc.close();
	}

}
