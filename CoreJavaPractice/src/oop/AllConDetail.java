package oop;

public class AllConDetail {
	static int a;
	static void m() {
		System.out.print("static");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AllCon s3=new AllCon("student",52,"ccc");
		AllCon s4=new AllCon("yutguf",78,"dd");
		
		
		s3.display();
		s4.display();
		
		m();
	

	}

}
