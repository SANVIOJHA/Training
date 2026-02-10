package streamApi;
import java.util.*;
import java.util.stream.Collectors;

import streamApi.Student;
public class CustomStudent {

	public static void main(String[] args) {
		  List<Student> st =Arrays.asList(
	        new Student(1,"abc"),
	        new Student(2,"bbfhd"),
	        new Student(3,"aaabc"),
	        new Student(4,"afhd"));
	        System.out.println(st);
	        
	        
	        List<Student> withA=st.stream()
	        		.filter(x->x.getName().startsWith("a"))
	        		.collect(Collectors.toList());
	        		System.out.println(withA);
	        		
	        		
	       
	        

	}

}
