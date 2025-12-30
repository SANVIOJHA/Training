package oop;



public class Student {
	String pan;
	String voter;
	String aadhar;
	
	Student(String p,String v ,String aa){
		this.pan=p;
		this.aadhar=aa;
		this.voter=v;
		//s4
	}
	
	Student(String p,String v ){
		this.pan=p;
		this.voter=v;
		//s3
	}
	
	Student(String aa){
		this.aadhar=aa;
		//s2
}
	Student(){
		//s1
}
	
	
	public static void main(String[] args) {
		Student s1=new Student();
		Student s2=new Student("aadhar123");
		Student s3=new Student("pan123","voter344");
		Student s4=new Student("pan123","voter344","aadhar123");
		
		System.out.println("s1== : "+s1);
		System.out.println("s2== : "+s2);
		System.out.println("s3== : "+s3);
		System.out.println("s4== : "+s4);
		
//		s1== : oop.Student@5ca881b5
//				s2== : oop.Student@4f023edb
//				s3== : oop.Student@3a71f4dd
//				s4== : oop.Student@7adf9f5f
		
		System.out.println("s2== : "+s2.aadhar);//s2== : aadhar123
		
	
		
		
		
		
	}

}
