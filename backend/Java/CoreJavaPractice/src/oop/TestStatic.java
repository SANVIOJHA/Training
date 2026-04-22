package oop;

public class TestStatic {
	static {
		System.out.println("static block");
	}
	
	//constructor
	TestStatic(){
	
			System.out.println("constructor: ");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main block");
		TestStatic d=new TestStatic();
	}
		
		static {
			System.out.println("static block after and outside main method");
			
			
		}
		
	

}
