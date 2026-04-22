package methodOverriding_Shapes;

public class Triangle extends Shapes{
	
	
	@Override
	public void side() {
		System.out.println("triangle has 3 sides");
	}

	@Override
	public void area() {
		System.out.println("triangle area is base* height");
		System.out.println(base*height);
	}
	
	
}
