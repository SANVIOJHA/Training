package objectOrientedEncapsulation;

public class ObjectAn {

	public static void main(String[] args) {
		Animal a1=new Animal("Dog");
		System.out.println(a1.name);
		Animal a2=a1;
		System.out.println(a2.name);
		a2.name="cat";
		System.out.println(a2.name);
		System.out.println(a1.name);///why output changed?
		

	}

}
