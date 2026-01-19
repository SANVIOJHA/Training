package sort_data_from_collections;
import java.util.*;
public class BookApp {
	public static void main(String[] args) {
		ArrayList<Book> a=new ArrayList();
		a.add(new Book(1000));
		a.add(new Book(400));
		a.add(new Book(14));
		a.add(new Book(15));
		a.add(new Book(120));
//		System.out.println(a);
		System.out.println("before sorting ");
		System.out.println(a);
		Collections.sort(a,new BookComparator());
		
		System.out.println("after sorting ");
		System.out.println(a);
		
		
	}
}
