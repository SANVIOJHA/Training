package treeset;

import java.util.TreeSet;

public class Custom {
	public static void main(String[] args) {
		TreeSet<Student> s=new TreeSet<>(new StudentComparator());
		s.add(new Student("v",1));
		s.add(new Student("a",12));
		s.add(new Student("s",3));
		s.add(new Student("f",10));
		System.out.println(s);
	}

}
