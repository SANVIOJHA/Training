package hasARelationShip_Practice_EarlyINstantiation;

public class Dept {
	private String depName;
	
	public String getDepName() {
		return depName;
	}
	
	public void setDepName(String depName) {
		this.depName=depName;
	}
	//constructor
	Dept(){
		
	}
	
	
	 Dept(String depName){
		 this.depName=depName;
	 }

}
