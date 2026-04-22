package abstraction;

//public class Dog extends Animal{//error why? ------ The type Dog must implement the inherited abstract method Animal.sound()
public class Dog extends Animal{
//add unimplemented method
	
	@Override
	public void sound() {
		System.out.println("dog bark....");

		
	}
	

}
