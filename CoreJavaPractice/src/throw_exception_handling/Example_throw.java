package throw_exception_handling;

public class Example_throw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=10;
		int b=0;
		
		if(b==0) {
//			throw new ArithmeticException();
			throw new WrongInputException("working");
		}
		else {
			System.out.println(a/b);
		}
	}

}
