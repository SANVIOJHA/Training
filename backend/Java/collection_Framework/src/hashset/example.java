package hashset;
import java.util.HashSet;

import array_list.Student_Add;

public class example {
	public static void main(String[] args) {
		HashSet h=new HashSet();
		h.add(16);
		h.add(18);
		h.add(32);
		h.add(null);
		System.out.println(h);
		System.out.println(h.contains(16));
		
		//for each
		for(Object o:h) {
			System.out.println(o);
		}
		
		HashSet s=new HashSet();
		s.add(6);
		s.add(5);
		s.add(12);
		s.add(null);
		s.add("373");
		System.out.println(s);
		
		h.add(s);
		System.out.println("h.add---   "+h);
		h.addAll(s);
		System.out.println("addAll----"+h);
		
		
		
		
	}
}
