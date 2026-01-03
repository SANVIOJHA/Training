
public class Sta {
	
	
	Sta(){
		System.out.println("constructor");
	}

	
	{
		System.out.println("non-static");
		
	}
	static {
		System.out.println("static");
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Sta();
		//System.out.println("main method");
		
		
	
	}

}
