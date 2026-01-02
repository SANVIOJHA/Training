package has_A_Relationship__lazyINstantiation;

public class Main {
	public static void main(String[] args) {
		Car c=new Car();
		c.addTyre(new Tyre("m"));
		c.addTyre(new Tyre("m"));
		c.addTyre(new Tyre("m"));
		c.addTyre(new Tyre("m"));
		
		for(int i=0;i<c.tyres.length;i++) {
			System.out.println(c.tyres[i].getName());
		}
	}
}
