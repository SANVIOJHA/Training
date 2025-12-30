package objectOrientedEncapsulation;
//OuterEx 
public class Outer {

	static class Inner{
		static void display() {
			System.out.println("static block");
		}
		//nonstatic
		void test() {
			System.out.println("non static");
		}
	}
	
}
