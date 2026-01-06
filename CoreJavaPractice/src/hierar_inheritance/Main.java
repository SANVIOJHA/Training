package hierar_inheritance;

public class Main {
	public static void main(String[] args) {
		Dog d=new Dog("dog",4,"omni","barks");
		d.info();
		d.soundd();
		
		System.out.println();
		Cat c=new Cat("cat",4,"omni","meow");
		c.info();
		c.soundd();
		
		System.out.println();
		
	
		Lion l=new Lion("lion",4,"carni","roar");
		l.info();
		l.soundd();
		
		System.out.println();
		
	}
}
