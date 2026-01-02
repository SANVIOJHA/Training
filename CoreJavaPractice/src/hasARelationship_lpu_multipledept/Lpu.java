package hasARelationship_lpu_multipledept;

public class Lpu {
	Dept[] deptName= {new Dept("cse"),
			new Dept("cse"),
			new Dept("cse"),
			new Dept("cse"),
			new Dept("cse"),
	};
	
	
	public Dept[] getDepts() {
		return deptName;
	}
	
	public void setDepts(Dept[] deptName) {
		this.deptName=deptName;
	}
	
	Lpu(){
		
	}
	Lpu(Dept[] deptName){
		this.deptName=deptName;
	}
	
}
