package sort_data_from_collections;
import java.util.*;
public class BookComparator implements Comparator<Book>{

	@Override
	public int compare(Book o1,Book o2) {
		return o2.price-o1.price;
//		return Integer.compare(o2.price, o1.price);///best practice

		
	}

}
