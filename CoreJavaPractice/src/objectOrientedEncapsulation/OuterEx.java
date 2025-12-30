package objectOrientedEncapsulation;

import objectOrientedEncapsulation.Outer.Inner;

///outer class 
public class OuterEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Outer.Inner.display();///only static block output
		//Outer.Inner.test();///doeesn't run
		
		
		
		//static
		Outer.Inner a=new Outer.Inner();
		a.display();
		
		
		//non static
//		Inner.test();/// error
		Inner inner = new Inner();
		inner.test();

	}

}
