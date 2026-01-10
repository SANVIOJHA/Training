package object_class_toString_Array_Example;
import java.util.*;



public class Main {

	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.println("enter phone count  ");
	int n=sc.nextInt();
	Phone_A[] phone = new Phone_A[n];  
	
	
	for(int i=0;i<n;i++) {
		System.out.println("phone  "+i+" details");
		System.out.println("ram is ");
		int ram=sc.nextInt();
		System.out.println("rom is ");
		int rom=sc.nextInt();
		
		phone[i]=new Phone_A(ram,rom);
	}
	
	for(int i=0;i<n;i++) {
		System.out.println(phone[i]);
		
	}
	
	
	

	}

}
