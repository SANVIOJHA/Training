package sort_data_from_collections;
import java.util.*;
public class BookAppComparable {
	public static void main(String[] args) {
		ArrayList<BookComparable> a=new ArrayList();
		a.add(new BookComparable(1000));
		a.add(new BookComparable(400));
		a.add(new BookComparable(14));
		a.add(new BookComparable(15));
		a.add(new BookComparable(120));
//		System.out.println(a);
		System.out.println("before sorting ");
		System.out.println(a);
		Collections.sort(a);
		System.out.println("after sorting ");
		System.out.println(a);
		
		
	}
}
