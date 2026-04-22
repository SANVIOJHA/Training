package sort_data_from_collections;

public class BookComparable implements Comparable<BookComparable>{
	int price;
	BookComparable(){}
	BookComparable(int price){
		this.price=price;
	}
	@Override
	public String toString() {
		return "Book [price=" +price+ "]";
	}
	public int compareTo(BookComparable o) {
		return this.price-o.price;
	}

}
