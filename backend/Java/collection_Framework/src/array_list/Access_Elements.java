package array_list;

import java.util.ArrayList;
import java.util.*;


public class Access_Elements {

	public static void main(String[] args) {
		ArrayList a=new ArrayList();
		a.add(12);
		a.add(345);
		a.add("ghm");
		a.add(3.115);
		a.add(null);
		a.add(true);
		System.out.println(a);
		
		//get(index)
		System.out.println(a.get(5));
	
		for(int i=0;i<a.size();i++) {
			System.out.println("at index "+i+" is "+a.get(i)   );
		}
		
		System.out.println();
		System.out.println("for each");
		//for each
		int i=0;
		for(Object o:a) {
			System.out.println("at index " +i+" is "+o);
			i++;
		}
		
		
		System.out.println();
		System.out.println("iterator ");
		//
		
		//iterator
		Iterator z=a.iterator();
		//either this run or while run at a time both is not running simultaneously
		System.out.println(z.next());
		System.out.println(z.next());
		System.out.println(z.next());
		System.out.println(z.next());
		System.out.println(z.next());
		System.out.println(z.next());
//		System.out.println(z.next());///java.util.NoSuchElementException
		
		System.out.println("iterator while ");
		while(z.hasNext()) {
System.out.println(z.next());
		}
		
		System.out.println();
		
		///////////////////////////////////////////////////////////////////////////////////////////////////
		//ListIterator
		System.out.println("Listiterator while ");
		ListIterator l=a.listIterator();
		while(l.hasNext()) {
			System.out.println(l.next());
		}
		System.out.println();
		System.out.println("Previous Listiterator while ");
//		ListIterator previous
		while(l.hasPrevious()) {
			System.out.println(l.previous());
		}
		
		
		while(l.hasNext()) {
			System.out.println(l.previous());
		}
		System.out.println();
		System.out.println("Reverse from index " );
		/////till specific element
		
		int index = 3; // reverse till index 3 (0-based)

		ListIterator<Integer> p = a.listIterator();

		/* Move forward till index */
		while (p.hasNext()) {
		    if (p.nextIndex() == index) {
		        break;
		    }
		    System.out.println(p.next());
		}

		System.out.println("Reverse from index " + index);

		/* Reverse traversal */
		while (p.hasPrevious()) {
		    System.out.println(p.previous());
		}

	}

}
