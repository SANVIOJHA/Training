package arraypractice;

import java.util.Scanner;


public class Union {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		int[] arr=new int[n];
		System.out.print("enter m: ");
		int m=sc.nextInt();
		int[] b=new int[m];
		
		System.out.println("enter arr elements: ");
		for(int i=0;i<n;i++) {
				arr[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++) {
			
			System.out.print(arr[i]+" ");	
		}
		
		System.out.println();
		System.out.println("enter b elements: ");
		for(int i=0;i<m;i++) {
				b[i]=sc.nextInt();
		}
		for(int i=0;i<m;i++) {
			
			System.out.print(b[i]+" ");	
		}
		
		System.out.println();
		
		int k=0;
		for(int i=0;i<n+m;i++) {
			arr[k++]=arr[i];
		}

		
		
		sc.close();
		
	}

}
