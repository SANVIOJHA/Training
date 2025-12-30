package array;

import java.util.Scanner;


//2)WAJP Print even index element in an array?
//4)WAJP Print odd index element in an array?
		
public class EvenIndex {

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
			if(i%2==0) {
				System.out.println(arr[i]+" is at even index which is "+i);
			}else {
				System.out.println(arr[i]+" is at odd index which is "+i);
			}
		}
		
		
		System.out.println();
		
		
		sc.close();
	}

}
