package hierar_inheritance;

public class Lion extends Animal{
	String sound;
	int legs;
	Lion(){
		
	}
	Lion(String name,int legs,String food,String sound){
		super(name,legs,food);
		this.sound=sound;
		
		
	}
	void soundd() {
		System.out.println("Lion roar");
	}
}
