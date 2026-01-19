package remove_data_from_collection;
import java.util.ArrayList;
public class RemoveAll {

	public static void main(String[] args) {
		ArrayList a=new ArrayList();
		a.add(1);
		a.add("e");
		a.add(787);
		a.add("avbd");
		a.add(45);
		a.add("jhf");

		
		ArrayList b=new ArrayList();
		b.add(45);
		b.add("e");
		b.add(787);
		b.add("gt");
		b.add(5);
		b.add("jhf");
		
		System.out.println("ArrayList 1------ "+a);
		System.out.println("ArrayList 2------ "+b);
	
		System.out.println();
		a.removeAll(b);//a me se saare b waale elements nikal do //remove same elements
		System.out.println(a);
		System.out.println(b);

	}

}
