package array_list;

import java.util.*;
public class AddingData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//can store both homo+hetero----geneus
		//when we want to change the size of list -----like dynamically
		//it allows duplicates and nulls
		//inbuilt methods can be used to add datas
		///Elements in an ArrayList can be accessed directly using an integer index (starting from zero).
		///  This provides fast, constant-time (O(1)) access to elements (retrieval using get() method).
		//insertion order is maintained
		ArrayList a=new ArrayList();
		a.add(12);
		a.add(345);
		a.add("ghm");
		a.add(345);
		a.add(3.115);
		a.add(null);
		a.add(true);
		System.out.println(a);
		
		//collection inside one collection
		
		ArrayList b=new ArrayList();
		b.add(5);
		b.add(345);
		b.add(3.115);
		b.add(null);
		b.add(true);
//		b.add(a);
//		System.out.println(b);

		//add(object)
		//addAll(collection)
//		b.addAll(a);
//		System.out.println(b);/////[5, 345, 3.115, null, true, 12, 345, ghm, 345, 3.115, null, true]///\
		
		//
//		add element at specific position
		
//		b.add(10,"name");
//		System.out.println(b);
		
		//
		//add all collection at starting
		
		b.addAll(0,a);
		System.out.println(b);
		 
	}

}
