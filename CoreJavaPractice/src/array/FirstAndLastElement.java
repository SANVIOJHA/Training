package array;

import java.util.Scanner;



//7)WAJP Print First Element and last element?

public class FirstAndLastElement {

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
			
			System.out.println(arr[i]+" ");	
		}
//		for(int i=0;i<n;i++) {
			System.out.println("first element is "+arr[0]);
			System.out.println("last element is "+arr[n-1]);
//		}
		
		
		sc.close();
		
	}

}
