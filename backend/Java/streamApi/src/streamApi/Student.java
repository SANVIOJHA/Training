package streamApi;

public class Student {
	private int age;
	private String name;
	
	Student(){
		
	}
	Student(int age,String name){
		this.name=name;
		this.age=age;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	
	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}

}
