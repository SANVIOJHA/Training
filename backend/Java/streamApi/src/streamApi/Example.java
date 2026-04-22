package streamApi;
import java.util.*;
import java.util.stream.Collectors;
public class Example {
	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<>();
		a.add(10);
		a.add(20);
		a.add(30);
		System.out.println(a);
		/////////stream///
		/// 
		a.stream()
		.forEach(System.out::println);
		List<String> names =Arrays.asList("She","kav","roh","yyiuhj","fghjklhgfdfghj");
		names.stream()
		.forEach(System.out::println);
		
		
		//count
		System.out.println("count--------------");
		long count=names.stream()
		.count();
		System.out.println(count);
		
		
		System.out.println();
		
		names.stream().forEach(System.out::println);
		System.out.println();
		names.forEach(System.out::println);
		System.out.println();
		
		///filter
		/// 
		System.out.println("filter ----------------");
		names.stream()
		.filter(s->s.length()>5)
		.forEach(System.out::println);
		
		
		////filter on numbers
		//
		List<Integer> num =Arrays.asList(1,4,23,2,5,2,2,5,2);
		num.stream()
		.filter(n -> n%2==0)
		.forEach(System.out::println);
		
		
		/// map
		//
		System.out.println("map");
		List<Integer> doublenum=num.stream()
		.map(x ->x*2)
		.collect(Collectors.toList());
		System.out.println(doublenum);
		System.out.println();
		
		
		//
	////	map on strings
		System.out.println("strings in upper case using map -------");
		List<String> str =Arrays.asList("She ","kav ","roh ","yyiuhj ","fghjklhgfdfghj ");
		str.stream()
		.forEach(System.out::print);
		System.out.println();
		
		List<String> upper=str.stream()
		.map(x->x.toUpperCase())
		.collect(Collectors.toList());
		System.out.println(upper);
		
	}
}
