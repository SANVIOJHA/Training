package hasARelationship;

public class Car {
	private String modelName;
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName=modelName;
	}
	Car(){
		
	}
	Car(String modelName){
		this.modelName=modelName;
		
	}
//	Engine e=new Engine(1000);   ///// if object creation is this then ---- System.out.println(c.e.getHp());---
	private Engine e=new Engine(1000);
	
	public Engine getEngine() {
	return e;
	}

	
}
