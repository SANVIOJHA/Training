package abstraction;

public class Main {
	public static void main(String[] args) {
		Cat c=new Cat();
		c.sound();
		System.out.println("below is upcasting: ");
		Animal a=new Cat();
		a.sound();
		a.walk();
	}
}
