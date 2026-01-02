package hasARelationship_lpu_multipledept;

public class Dept {
	private String name;
	
	public String getDept() {
		return name;
	}
	
	public void setDept(String name) {
		this.name=name;
	}
	
	//constructor
	Dept(){
		
	}
	
	Dept(String name){
		this.name=name;
	}
	

}
