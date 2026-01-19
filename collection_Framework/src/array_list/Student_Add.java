package array_list;

public class Student_Add {
	String name;
	int id;
	Student_Add(){
		
	}
	
	
	Student_Add(String name,int id){
		this.name=name;
		this.id=id;
	}
@Override
public String toString() {
	return "Student [name= "+name+ " , id= "+ id + "] ";
}

//for custom searching
public boolean Student_Add(Object o) {
	return false;
	
}
	
}
