package streamApi;
import java.util.*;
import java.util.stream.*;

public class Examples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<Integer> num =Arrays.asList(1,4,23,2,5,2,2,5,2);
//		System.out.println("evens are : "+" ");
//		num.stream()
//		.filter(n -> n%2==0)
//		.forEach(System.out::println);
//		
//		System.out.println("odds are : "+" ");
//		num.stream()
//		.filter(n -> n%2!=0)
//		.forEach(System.out::println);
//		
		
		
//		List<Integer> n=Arrays.asList(1,4,23,2,5,2,2,5,2);
//		n.stream().foreach(x->{
//				if(x%2==0) {
//					System.out.println("evens: ");
//		
//		}else {
//			System.out.println("odds: ");
//			
//		};
//		//partitioningBy---- is needed as it will collect the elements and will check with the conditions  
/*
 * Stream converts the list to a stream, partitioningBy classifies elements into two lists based on a boolean condition, 
 	and collect() stores the result in a map.
 	*/
//		1.
		List<Integer> list=Arrays.asList(1,4,23,2,5,2,2,5,2);
		Map<Boolean,List<Integer>> a=list.stream().collect(Collectors.partitioningBy(x->x%2==0));
			System.out.println(a.get(true)+" even ");
			System.out.println(a.get(false)+" odds");
			
		
				
//		2.
		List<String> s=Arrays.asList("a","b","a");
		System.out.println("no duplicates: ");
		s.stream()
		.distinct()
		.forEach(System.out::println);
		
		

		
		
		

	}

}
