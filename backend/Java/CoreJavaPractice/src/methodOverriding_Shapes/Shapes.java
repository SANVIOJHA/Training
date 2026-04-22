package methodOverriding_Shapes;

public class Shapes {
	int side;
	double length;
	double breadth;
	double base;
	double height;
	double radius;
	Shapes(){
		
	}
	
	Shapes(int side,double length,double breadth,double base,double height,double radius){
		this.side=side;
		this.length=length;
		this.breadth=breadth;
		this.base=base;
		this.height=height;
		this.radius=radius;
	}
	public void side() {
		System.out.println("Every shapes has side");
	}
	public void area() {
		System.out.println("Every shapes has area");
	}
}
