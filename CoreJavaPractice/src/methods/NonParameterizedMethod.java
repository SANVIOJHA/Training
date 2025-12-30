package methods;


public class NonParameterizedMethod {
	
	static void areaOfCircle() {
		int r=6;
		float pi=3.14f;
		float area=pi*(r*2);
		System.out.println("area of circle "+ area);
	}
	
	static void areaOfRectangle() {
		int a=6;
		int b=9;
		
		int area=a*b;
		System.out.println("area of rectangle: " +area);
	}
	
	static void areaOfTriangle() {
		float h=6f;
		float b=9f;
		
		double area=(0.5)*(h*b);
		System.out.println("area of triangle: " +area);
	}
	
	static void areaOfSquare() {
		int a=6;
		int area=a*2;
		System.out.println("area of square: "+area);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		areaOfCircle();
		areaOfRectangle();
		areaOfTriangle();
		areaOfSquare();
		
		
	}

}
