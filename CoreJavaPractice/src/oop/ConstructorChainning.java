package oop;

public class ConstructorChainning {
	 String pan;
	String voter;
	String aadhar;

	ConstructorChainning(){
		//s1
}
	
	ConstructorChainning(String aa){
		this();
		this.aadhar=aa;
		//s2
}
	
	
	ConstructorChainning(String p,String v ){
		this.pan=p;
		this.voter=v;
	//s3
}
	
	ConstructorChainning(String p,String aa ,String v){
		this(p,aa);
		this.voter=v;
		//s4
	}
	
	
	void display() {
	    System.out.println("PAN: " + pan + 
	                       ", VOTER: " + voter + 
	                       ", AADHAR: " + aadhar);
	}
	public static void main(String[] args) {
		ConstructorChainning s1=new ConstructorChainning();
		ConstructorChainning s2=new ConstructorChainning("aadhar123");
		ConstructorChainning s3=new ConstructorChainning("pan123","voter344");
		ConstructorChainning s4=new ConstructorChainning("pan123","voter344","aadhar123");
		
		System.out.println("s1== : "+s1);
		System.out.println("s2== : "+s2);
		System.out.println("s3== : "+s3);
		System.out.println("s4== : "+s4);
		
//		s1== : oop.Student@5ca881b5
//				s2== : oop.Student@4f023edb
//				s3== : oop.Student@3a71f4dd
//				s4== : oop.Student@7adf9f5f
		
		System.out.println("s2== : "+s2.aadhar);//s2== : aadhar123
		
		System.out.println();
		System.out.println();
		
		s1.display();
		s2.display();
		s3.display();
		s4.display();
	
		
		
		
		
	}
	


}
