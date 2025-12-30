package operators;


public class IncreamentDecreament {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=5;
		int b=(a++) + (++a) + a;
		int c=(--a) + (++b) + (b--);
		int d= a+b+c+(a--);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
	

}
