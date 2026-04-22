package objectOrientedEncapsulation;

import objectOrientedEncapsulation.Outer.Inner;

///outer class 
public class OuterEx {

	public static void main(String[] args) {
		
		Outer.Inner.display();///only static block output
		//Outer.Inner.test();///doesn't run
		
		
		
		//static
		Outer.Inner a=new Outer.Inner();
		a.display();
///		System.out.println(a.display());//this method println(boolean) in the type PrintStream is not applicable
///  for the arguments (void)
		
		
		//non static
//		Inner.test();/// error
		Inner inner = new Inner();
		Inner b=new Inner();
///		Outer.Inner.b()///error

		inner.test();
///	System.out.println(inner.test()); //The method println(boolean) in the type PrintStream is not applicabl
/// e for the arguments (void)
		

	}

}
