package inheritance_Super;

public class Student extends Person{
	int age=90;
	public void display() {
		System.out.println(name);
		System.out.println(age);
	}
	Student(){
		
	}
	Student(String name,int age){
		super(name);
		this.age=age;
		
	}
	public static void main(String args[]) {
		Student s=new Student("om",67);
		m();
		s.display();
	}
}
