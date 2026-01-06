package multilevel_inheritance;

public class Triangle extends Shape{
	//int side=4;
	String name="eqi";
	int angle=88;
	
	Triangle(){
		
	}
	Triangle(int side,String name,int angle){
		super(side);
		this.name=name;
		this.angle=angle;
		
	}
	
	
}
