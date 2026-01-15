package throws_exception_handling;

public class Throws_e {
	public static void m() throws ArithmeticException, NullPointerException {
		int a=10;
		int b=0;
		System.out.println(a/b);
	}
	public static void m2() {
		try {
			m();
		}catch(Exception e) {
			System.out.println("caught");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		m2();
	}

}
