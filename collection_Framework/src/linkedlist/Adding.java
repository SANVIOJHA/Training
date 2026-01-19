package linkedlist;

import java.util.*;

public class Adding {
	public static void main(String[] args) {
		LinkedList list=new LinkedList();
		list.add(10);
		list.add(90);
		list.add(1);
		list.add("hello");
		list.add("key");
		list.add(null);
		System.out.println(list);
		LinkedList b=new LinkedList();
		b.add(5);
		b.add(345);
		b.add(3.115);
		b.add(null);
		b.add(true);
		System.out.println(b);
		
		list.add(b);
		System.out.println(list);//concat both linkedlist
		System.out.println();
		list.addAll(b);
		System.out.println(list);
		
}
}
