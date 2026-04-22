package wrapperClass;

public class UnBoxing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//boxing
				int b=20;
				Integer boxed=Integer.valueOf(b);
				System.out.println("now "+boxed+" is boxed");
	//unboxing
				int unboxed=boxed.intValue();
				System.out.println("now "+unboxed+" is unboxed");
				
				
				byte x=50;
				Byte y=Byte.valueOf(x);
				System.out.println("Byte "+y);

	}

}
