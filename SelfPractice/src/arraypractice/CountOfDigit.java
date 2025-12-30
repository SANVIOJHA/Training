package arraypractice;
import java.util.*;
public class CountOfDigit {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("enter " );
		int n=sc.nextInt();
		int count=0;
		while(n>0) {
			n/=10;
			count++;
		}
		System.out.print(count);
		
		
		
		
		
		
		
		
		sc.close();
	}

}
