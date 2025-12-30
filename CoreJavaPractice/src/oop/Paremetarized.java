package oop;

public class Paremetarized {
	 String title;
	 int price;

	 
	 
	 
	 
	 
	public static void main(String srgs[]) {
		Book b1=new Book("java",699);
		Book b2=new Book("name",890);
		
//		b1.title="java";
//		b1.price=9000;
//		
//		b2.title="py";
//		b2.price=780;
		
		System.out.println(b1.title);
		System.out.println(b1.price);
		System.out.println(b2.title);
		System.out.println(b2.price);
		
	}

}