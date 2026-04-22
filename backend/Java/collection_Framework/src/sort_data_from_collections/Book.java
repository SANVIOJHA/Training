package sort_data_from_collections;
import java.util.*;

public class Book {
	int price;
	Book(){}
	Book(int price){
		this.price=price;
	}
	@Override
	public String toString() {
		return "Book [price=" +price+ "]";
	}
//	public int compareTo(Book o) {
//		return this.price-o.price;
//	}

}
