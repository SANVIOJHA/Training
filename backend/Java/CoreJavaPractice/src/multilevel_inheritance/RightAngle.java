package multilevel_inheritance;

public class RightAngle extends Triangle{
	//int side=3;
	//String name="equi";
	//int angle=90;
	int count=5;
	
	RightAngle(){
		
	}
	RightAngle(String name,int angle,int count){
		this.name=name;
		this.angle=angle;
		this.count=count;
		
	}
	public void display() {
	//	System.out.println("right angle one side: "+side);
		System.out.println("triangle one side: "+super.side);
		System.out.println("shape one side: "+((Shape)this).side);
		System.out.println("name: "+name);
		System.out.println("angle: "+angle);
		System.out.println("count: "+count);
	}
	
}
