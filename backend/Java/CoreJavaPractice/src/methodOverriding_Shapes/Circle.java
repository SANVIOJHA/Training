package methodOverriding_Shapes;

public class Circle extends Shapes{
	double pi=3.14d;
	@Override
	public void side() {
		System.out.println("circle is round");
	}

	@Override
	public void area() {
		System.out.println("circle area is pi*radius^2");
		System.out.println(pi*(radius*radius));
	}
}
