package hierar_inheritance;

public class Cat extends Animal{
	String sound;
	int legs;
	
	Cat(){
		
	}
	Cat(String name,int legs,String food,String sound){
		super(name,legs,food);
		this.sound=sound;
		
		
	}
	void soundd() {
		System.out.println("Cats meow");
	}
}
