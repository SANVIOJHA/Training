package hasARelationship_EarlyINstantiation;

public class Main {
	public static void main(String args[]) {
		Car c=new Car("BMW");
		System.out.println(c);
		System.out.println(c.getModelName());
		
//		System.out.println(c.e.getHp());
		System.out.println(c.getEngine().getHp());
		
	}
}
