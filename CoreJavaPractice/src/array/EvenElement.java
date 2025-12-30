package array;

import java.util.Scanner;


//3)WAJP Print even element in an array?
//5)WAJP Print odd element in an array?
		
public class EvenElement {

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
			if(arr[i]%2==0) {
				System.out.println(arr[i]+" is  even  which is at index  "+i);
			}else {
				System.out.println(arr[i]+" is  odd index which is at index  "+i);
			}
		}
		
		
		System.out.println();
		
		sc.close();
		
	}

}
