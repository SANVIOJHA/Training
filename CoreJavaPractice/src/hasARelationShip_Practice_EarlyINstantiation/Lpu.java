package hasARelationShip_Practice_EarlyINstantiation;

public class Lpu {

	private String headName;
	private int empCount;
	//name
	
	public String  getHeadName(){
		return headName;
	}
	
	public void setHeadName(String headName) {
		this.headName=headName;
	}
	
//	//emp
	public int getEmpCount() {
		return empCount;
	}
	public void setEmpCount(int empCount) {
		this.empCount=empCount;
	}
	//contructor
	Lpu(){
		
	}
	Lpu(String headName,int empCount){
		this.headName=headName;
		this.empCount=empCount;
	}
	
	Dept d=new Dept("cse");
	
	
}
