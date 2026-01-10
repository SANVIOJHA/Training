package object_class_equals;

public class Student {
String name;
int id;
	Student(String name,int id){
		this.name=name;
		this.id=id;
	}
	
	public boolean equals(Object o) {
		Student s=(Student)o;
		if (this.name==s.name && this.id==s.id) {
			return true;
		}else {
			return false;
		}
	}
}
