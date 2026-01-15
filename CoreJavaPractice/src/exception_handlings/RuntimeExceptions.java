package exception_handlings;

public class RuntimeExceptions {

	public static void main(String[] args) {
		try {
			System.out.println(10/0);
			String a=null;
			System.out.println(a);
			 int num = Integer.parseInt("rtyjhgghj"); 
		}
		catch(ArithmeticException | NullPointerException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		try {
			int arr[]= {4567,567,98,0};
			String c="123";
			System.out.println(c);
			System.out.println(arr[arr.length+1]);
		}
		catch(NullPointerException|ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		finally {
			System.out.print("asdfghjgfzxjhgfddfgh");
		}
		
	}

}
