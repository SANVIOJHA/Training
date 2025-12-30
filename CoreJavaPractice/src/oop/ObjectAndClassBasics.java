package oop;

public class ObjectAndClassBasics {
	String title;//instance variables
	int price;

	public static void main(String[] args) {
		
//		System.out.println(new ObjectAndClassBasics());
//		System.out.print(new ObjectAndClassBasics());
//		
//		oop.ObjectAndClassBasics@5ca881b5
//		oop.ObjectAndClassBasics@24d46ca6
		
//		String title;//local  variables
//		int price;
//		
		ObjectAndClassBasics b1=new ObjectAndClassBasics ();
		ObjectAndClassBasics b2=new ObjectAndClassBasics();
		
		b1.title="java";
		b1.price=9000;
		
		b2.title="py";
		b2.price=780;
		
		System.out.println(b1.title);
		System.out.println(b1.price);
		System.out.println(b2.title);
		System.out.println(b2.price);
		
	}
}
