package wrapperClass;

public class BoxingEx {
	public static void main(String[] args) {
		//boxing
		int b=20;
		Integer boxed=Integer.valueOf(b);
		System.out.println("now "+boxed+" is boxed");
		
		//Autoboxing
		int a=303;
		Integer autoBoxed=a;
		System.out.println("now "+autoBoxed+" is autoboxed");
		//unboxing
		
		int unboxed=boxed.intValue();
		System.out.println("now "+unboxed+" is unboxed");
	}
}
 