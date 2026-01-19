package vector;
import java.util.*;
public class Basics {
	public static void main(String[] args) {
		Vector v=new Vector();
		v.add(10);
		v.addElement("5678");
		v.add(10);
		v.add(null);
		v.add(1200.5);                                              
		System.out.print(v);
		System.out.println();
		
		
		//foreach
		int i=0;
		for(Object o:v) {
			System.out.println("vector at index "+i+" is :"+o);
			i++;
		}
		System.out.println();
		
		//Accessing element
		System.out.println("element at index 4 is "+v.get(4));
		System.out.println("element at index 2 is "+v.elementAt(2));
		
		//updating element
		System.out.println("updating element at index 4 is "+v.set(1,4000));
	
		//removing 
		System.out.print("initially vector was : "+v);
		v.remove(3);
		System.out.println("removing element at index 3 is "+v);
		
		//searching
		System.out.println("is null there?  : "+v.contains(null));
		System.out.println("is 10 there?  : "+v.contains(10));
		
		
		//size , capacity
		
		System.out.println("length of vector ?  : "+v.size());//number of elements
		System.out.println("capacity ?  : "+v.capacity());//space allocated == numberof elements*2+2
		
		//iterator 
System.out.println();
System.out.println("iterator");
		Iterator it = v.iterator();
		while(it.hasNext()) {
		    System.out.println(it.next());
		}

		
	}
}
