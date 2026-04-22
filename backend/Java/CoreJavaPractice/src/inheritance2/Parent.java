package inheritance2;

public class Parent {
	int a=90;
	static int b=67;
	//initializer
	static {
		System.out.println("static initializer");
	}
	
	 {
			System.out.println("non -static initializer");
		}
	 
	 //////
	 ///   
	 
	
	static void staticMethod() {
		System.out.println("static method");
	}
	
	 void nonStatic() {
		 System.out.println("non-static method");
	 }
	public static void m() {
		System.out.println("parent ");
	}

}
