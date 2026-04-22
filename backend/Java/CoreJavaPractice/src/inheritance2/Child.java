package inheritance2;

public class Child extends Parent {
	public void display() {
		System.out.println(a);//non static variable
		System.out.println(b);//static variable
	}
	public static void main(String[] args) {
		Child c=new Child();
		c.display();
		staticMethod(); // static method is inherited
//		nonStatic();/// this will give error because Cannot make a static reference to the non-static method nonStatic()
///

		c.nonStatic();
 
	}

}
