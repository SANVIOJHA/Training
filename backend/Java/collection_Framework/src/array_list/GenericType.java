package array_list;

import java.util.ArrayList;

public class GenericType {

	public static void main(String[] args) {
///generic <data type>()
		ArrayList <Integer> a = new ArrayList<Integer>();
		a.add(12);
		a.add(345);
		a.add(12);
		a.add(35);
		//a.add("ghm");
		//a.add(3.115);
		//a.add(null);
		//a.add(true);

		System.out.println(a);
		//simple collection sum 
		int sum=0;
		for(int i=0;i<a.size();i++) {
			sum+=a.get(i);
			
		}
		System.out.println(sum);
		
		//collection inside collection sum
		
		ArrayList <Integer> b= new ArrayList<Integer>();
    	b.add(12);
    	b.add(5);
    	b.add(2);
    	b.add(5);
		
		System.out.println(b.addAll(a));
//		System.out.println(b.add(a));///since we are using generic so add is for object 
		
		
		
		int s=0;
		


	}

}
