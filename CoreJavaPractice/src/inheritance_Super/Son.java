package inheritance_Super;

public class Son extends Father{
	String name="abc";
	public void display() {
		System.out.println("father: "+super.name);
		System.out.println("father: "+this.name);
	}
	public static void main(String[]args) {
		Son s=new Son();
		s.display();
	}

}
