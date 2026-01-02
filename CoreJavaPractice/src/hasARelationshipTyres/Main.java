package hasARelationshipTyres;

public class Main {
	public static void main(String [] args) {
		Car c=new Car();
		
		for(int i=0;i<c.tyres.length;i++) {
			System.out.println(c.tyres[i].getName());
		}
//		for(int i=0;i<c.getTyre().length;i++) {
//			System.out.println(c.getTyre()[i].getName());
//		}
		
	}

}
