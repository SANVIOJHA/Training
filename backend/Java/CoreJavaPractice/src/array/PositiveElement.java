package array;

//6)WAJP Print positive even element in an array?

//in here i have added all other conditions
//
import java.util.Scanner;



		
public class PositiveElement {

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
		for(int i=0;i<n;i++) {
			
//			System.out.print(arr[i]+" ");
			if(arr[i]==0) {
				System.out.println(arr[i]+" is zero which is at index  "+i);
			}
		else if(arr[i]>=0 && arr[i]%2==0) {
				System.out.println(arr[i]+" is  positive and even which is at index  "+i);
			}else if(arr[i]<=0 && arr[i]%2==0) {
				System.out.println(arr[i]+" is  negative and even which is at index  "+i);
			}else if(arr[i]>=0 && arr[i]%2!=0) {
				System.out.println(arr[i]+" is  positive  and odd which is at index  "+i);
			}else {
				System.out.println(arr[i]+" is  negative   and odd which is at index  "+i);
			}
		}
		
		
		System.out.println();
		
		sc.close();
		
	}

}
