package linkedhashset;
import java.util.*;

public class Example {
	public static void main(String[] args) {
		LinkedHashSet l=new  LinkedHashSet();
		l.add(10);
		l.add(11);
		l.add(12);
		l.add(13);
		l.add("a");
		l.add("b");
		l.add(null);
		System.out.println(l);
		
		LinkedHashSet l1=new  LinkedHashSet();
	    l1.add(10);
		l1.add(11);
		l1.add(12);
		l1.add(13);
		l1.add("aa");
		l1.add("b");
		l1.add(null);
		System.out.println(l1);
		
//		l.add(l1);
//		System.out.println(l);
		l.addAll(l1);
		System.out.println(l);
		
		//for each
		for(Object o:l) {
			System.out.println(o);
		}
		
		//
		
		
		
	}
}
