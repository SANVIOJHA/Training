package methodOverriding_Shapes;
import java.util.*;
public class Main {
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.println("enter side: 3 for triangle  ");
	System.out.println("enter side: 4 for rectangle ");
	System.out.println("enter side: 0 for circle ");
	System.out.println("enter side: ");
	int side=sc.nextInt();
	Shapes s=new Triangle();
	Shapes s1=new Rectangle();
	Shapes s2=new Circle();
	
	switch(side) {
	case 3:{
		System.out.println("its triangle :");
		System.out.println("enter base: ");
		double base=sc.nextDouble();
		System.out.println("enter height: ");
		double height=sc.nextDouble();
		s.base=base;
		s.height=height;
		s.side();
		s.area();
	}
	break;
	
	case 4:{
		System.out.println("its rectangle :");
		System.out.println("enter length: ");
		double length=sc.nextDouble();
		System.out.println("enter breadth: ");
		double breadth=sc.nextDouble();
		s1.length=length;
		s1.breadth=breadth;
		s1.side();
		s1.area();
	}
	break;
	
	case 0:{
		System.out.println("its circle :");
		System.out.println("enter radius: ");
		double radius=sc.nextDouble();
		s2.radius=radius;
		s2.side();
		s2.area();
		
	}
	break;
	
	default:System.out.println("enter valid side (0,3,4)");
	break;
	}
	
	
	
	
	
	
	 
	
	
	
}
	
}
