package abstraction_example;

public class Main {
	public static void main(String[] args) {
		
		Shape s=new Triangle();
		s.angle();
		System.out.println();
		Shape s1=new Rectangle();
		s1.angle();
		System.out.println();
		Shape s2=new Circle();
		s2.angle();
		
	}
}
