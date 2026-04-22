package hierar_inheritance;

public class Animal {
	String name;
	int legs;
	String food;

	Animal(){
		
	}
	Animal(String name,int legs,String food){
		this.name=name;
		this.legs=legs;
		this.food=food;
	}
	
	void info() {
        System.out.println("Name: " + name);
        System.out.println("Legs: " + legs);
        System.out.println("Food: " + food);
    }
}
