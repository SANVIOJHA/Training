package lambdaExpression;

import static lambdaExpression.InterfaceClass.*;

public class Main   {
	public static void main(String[] args) {
		A a=()->{
			System.out.println("hello");
		};
		
		a.test();
		B z=(x,y)->{
			return x+y;
		};
		System.out.println(z.add(10, 60));
	}
}
