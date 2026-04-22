package hierar_inheritance;

public class Dog extends Animal {
	String sound;
	//int legs;
	Dog(){
		
	}
	Dog(String name,int legs,String food,String sound){
		super(name,legs,food);
		this.sound=sound;
		
	}
	void soundd() {
		System.out.println("Dogs barks");
	}
}
