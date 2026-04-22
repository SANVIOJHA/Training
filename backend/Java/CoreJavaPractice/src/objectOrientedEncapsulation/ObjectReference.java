package objectOrientedEncapsulation;

 class Box{
	
}

class Bottle{
	
}

//objectOrientedEncapsulation.Box@24d46ca6
//objectOrientedEncapsulation.Bottle@372f7a8d

public class ObjectReference {
		
//	static class Box{
//		
//	}
//	
//	static class Bottle{
//		
//	}
	
//	for above output will be-------
	
//	objectOrientedEncapsulation.ObjectReference$Box@24d46ca6
//	objectOrientedEncapsulation.ObjectReference$Bottle@372f7a8d
	
	public static void main(String[] args) {
		
		
		Box b1=new Box();
		//System.out.println(b1);
		printRef(b1);
		
		Bottle a=new Bottle();
		printRef(a);
		
		
		

	}
	public static void printRef(Box b1) {
		System.out.println(b1);
	}
	public static void printRef(Bottle a) {
		System.out.println(a);
	}

}
