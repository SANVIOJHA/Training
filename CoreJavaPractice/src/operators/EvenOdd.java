package operators;


public class EvenOdd {

	public static void main(String[] args) {
		int a=6;
		
		if(a%2==0) {
			System.out.println(a +" is even");
		}else {
			System.out.println(a+" is odd");
		}
		
		String res =(a%2==0)?"Even":"Odd";
		System.out.println(res);
	}

}
