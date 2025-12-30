package array;

import java.util.Scanner;
public class UsingNewKeywordArray {

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
			
			System.out.print(arr[i]+" ");
			
		}
		System.out.println();
		System.out.println("print "+ arr[n-1]);
		
		
		sc.close();
	}

}
