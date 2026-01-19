package remove_data_from_collection;
import java.util.ArrayList;
public class Remove {

	public static void main(String[] args) {
		ArrayList a=new ArrayList();
		a.add(1);
		a.add("e");
		a.add(787);
		a.add("avbd");
		a.add(45);
		a.add("jhf");
		
		System.out.println("ArrayList 1------ "+a);
		System.out.println("removing index 0 --- "+a.remove(0));
		System.out.println("after removing "+a);
		System.out.println();
		
		

	}

}
