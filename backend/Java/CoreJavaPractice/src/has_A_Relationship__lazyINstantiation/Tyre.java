package has_A_Relationship__lazyINstantiation;

public class Tyre {
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
		
	}
	
	Tyre(){
		
	}
	Tyre(String name){
		this.name=name;
		
	}
}
