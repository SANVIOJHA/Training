package object_class_equals;

public class StudentApp {

	public static void main(String[] args) {
		Student s1=new Student("Kavya",1);
		Student s2=new Student("Kavya",1);
		Student s3=new Student("abc",1);
		Student s4=new Student("Kavykka",1);
		
		// Student s1=s2;
		
		System.out.println("s1==s2: "+(s1==s2));
		System.out.println("s1.equals(s2)  "+(s1.equals(s2)));
		System.out.println(s1.hashCode()==s2.hashCode());
		
		System.out.println(s1.hashCode());
		System.out.println(s4.hashCode());
		
		System.out.println(s1.getClass());
//		System.out.println(t.getClass());/////Cannot invoke getClass() on the primitive type boolean  boolean t=(s4==s1);
		

	}

}
