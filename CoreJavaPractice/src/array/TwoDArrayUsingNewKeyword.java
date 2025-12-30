package array;

import java.util.Scanner;
public class TwoDArrayUsingNewKeyword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("enter n: ");
		int n=sc.nextInt();
		System.out.print("enter m: ");
		int m=sc.nextInt();
		int[][] arr=new int[n][m];
		
		System.out.println("enter arr elements: ");
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=sc.nextInt();	
			}
			
			
			
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(arr[i][j]+"  ");	
			}
			
			System.out.println();	
		}
		
//		System.out.println("print "+ arr[n-1]);
		
		sc.close();
	}

}
