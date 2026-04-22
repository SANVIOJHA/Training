package array_list;

import java.util.ArrayList;

public class Searching {

	public static void main(String[] args) {
		ArrayList a=new ArrayList();
		a.add(12);
		a.add(345);
		a.add("ghm");
		a.add(345);
		a.add(3.115);
		a.add(null);
		a.add(true);
		System.out.println(a);
		//if the element is present
		
		System.out.println(a.contains(345));
		System.out.println(a.contains(5));
		
		///at what position
		//
		System.out.println(a.indexOf(12));
		

	}

}
