package oop;
///non static
/// 
/// 
public class Box {
	double length;
	double breadth;
	///contructor
	Box(double l,double b){
		this.length=l;
		this.breadth=b;
	}
	
	//non-static methods
	double getArea() {
		return length*breadth;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box b1=new Box(8,9);
		
		System.out.println(b1.getArea());
		
		
	}

}
