package linkedlist;
import java.util.*;
public class Basic {
	public static void main(String[] args) {
		LinkedList list=new LinkedList();
		list.add(10);
		list.add(90);
		list.add(1);
		list.add("hello");
		list.add("key");
		list.add(null);
		System.out.println(list);
		//searching element
		
		System.out.println(list.contains(90));
		System.out.println(list.contains(0));
		
		
		//for each
		int i=0;
		for(Object o:list) {
			System.out.println("elements of linkedlist are : at index "+i+ " is "+o);
			i++;
		}
		System.out.println();
		//iterator
		System.out.println("iterator");
		Iterator it=list.iterator();
		
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		
		
		//
		
	}
}
