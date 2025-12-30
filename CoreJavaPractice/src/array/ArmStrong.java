package array;
import java.util.*;
//14)WAJP Print Armstrong element in an array

public class ArmStrong {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		int n=sc.nextInt();
		int[] arr=new int[n];
		int count=0;
		int sum=0;
		int ori=n;
		
		System.out.print("enter elements : ");
		for( int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		System.out.print("[");
		for( int i=0;i<n;i++) {
			System.out.print(arr[i]+" ");
		}System.out.print("]");
		
		
		
		while(n>0) {
			n/=10;
			count++;
		}
		
		for(int i=0;i<n;i++) {
			int digit=n%10;
			sum+=Math.pow(digit, count);
			n/=10;
			
		}
		System.out.println();
		if(ori==sum) {
		System.out.print("is armstrong");
		}else {
			System.out.print("is not armstrong");
		}
		
		
		
		
		
		
		sc.close();
	}

}
