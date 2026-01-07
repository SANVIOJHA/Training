package methodOverriding_Shapes;

public class Rectangle extends Shapes{
	@Override
	public void side() {
		System.out.println("rectangle has 4 sides");
	}

	@Override
	public void area() {
		System.out.println("rectangle area is length * breadth");
		System.out.println(length*breadth);
	}

}
