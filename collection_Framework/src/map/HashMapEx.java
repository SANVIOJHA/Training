package map;
import java.util.*;
public class HashMapEx {
	public static void main(String[] args) {
		HashMap h=new HashMap();
		//same key --- so last element will be store only
		h.put(1,"Shraddha");
		h.put(1,"Saddha");
		h.put(1,"addha");
		h.put(1,"Sddha");
		h.put(1,"Shr");
		System.out.println(h);
		
		///
		HashMap hm=new HashMap();
		//different key --- 
		hm.put(1,"Shraddha");
		hm.put(2,"Saddha");
		hm.put(3,"addha");
		hm.put(4,"Sddha");
		hm.put(5,"Shr");
		hm.put(15,"Shraddha");
		hm.put(1,"a");
		hm.put(1,null);
		hm.putIfAbsent(1, "absent");
		System.out.println(hm);
		
		
		//only key should be displayed
		System.out.println(hm.keySet());
		//only values shoud be displayed
		System.out.println(hm.values());
		//all entries
		System.out.println(hm.entrySet());
		
		//for each
		System.out.println("entryset");
		for(Object o:hm.entrySet()) {
			System.out.println(o);
		}
		System.out.println();
		System.out.println("keyset");
		for(Object o:hm.keySet()) {
			System.out.println(o);
		}
		
		System.out.println();
		System.out.println("values");
		for(Object o:hm.values()) {
			System.out.println(o);
		}
	}
}
