package objectOrientedEncapsulation;

public class MethodChaining {
	MethodChaining m1() {
		return this;
	}
	MethodChaining m2() {
		return this;
	}
	MethodChaining m3() {
		return this;
	}
	MethodChaining m4() {
		return this;
	}
	
	public static void main(String[] at) {
		MethodChaining a=new MethodChaining();
		MethodChaining obj=a.m1().m2().m3().m4();
		System.out.println(a);
		System.out.println(obj);
	}

}
