package object_class_equals;

public class StudentApp {

	public static void main(String[] args) {
		Student s1=new Student("Kavya",1);
		Student s2=new Student("Kavya",1);
		Student s3=new Student("abc",1);

		
		// Student s1=s2;
		
		System.out.println("s1==s2: "+(s1==s2));
		System.out.println("s1.equals(s2)  "+(s1.equals(s2)));
		
		
		

	}

}
