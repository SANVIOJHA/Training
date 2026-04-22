package basics_practice;

public class String_Buffer {
public static void main(String[] args) {
		
		
		StringBuffer s1=new StringBuffer();
		System.out.println(s1);
		//append
		s1.append("hello");
		System.out.println(s1);
		
		//insert
		s1.insert(1," welcome ");
		System.out.println(s1);
		//reverse
		s1.reverse();
		System.out.println(s1);
		//replace
		s1.replace(1,3,"qwerty");
		System.out.println(s1);
		System.out.println(s1.reverse());
		//delete
		StringBuffer s2=new StringBuffer("hellohiby3");
		System.out.println(s2.delete(2,s2.length()-1));
		//capacity
		StringBuffer s3=new StringBuffer("  jhgfdsdfghjklsdfghjklkjhgfghjklysyuioifdsdfghjkkjhgxfghjklkjhgfdasdfghjklvbnmdfghjkghj");
		s3.append("asdfghjklsdfghjklgfzxcvbnmsrtyuiiuytdfgh");
		System.out.println(s3.capacity());
		
}
}
