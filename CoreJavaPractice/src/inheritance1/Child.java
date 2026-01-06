package inheritance1;

public class Child extends Parent {
	public void display() {
		System.out.println(a);
	}
	public static void main(String[] args) {
		Child c=new Child();
		c.display();
	}
}
