package methodOverriding;

public class Main {

	public static void main(String[] args) {
	Animal a=new Dog();
	a.walk();
	a.sound();
	
	Animal b=new Cat();
	a.walk();
	b.sound();

	}

}
