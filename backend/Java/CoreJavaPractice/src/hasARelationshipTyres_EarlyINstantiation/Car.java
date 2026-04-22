package hasARelationshipTyres_EarlyINstantiation;

public class Car {
	Tyre[] tyres= {new Tyre("M"),
			new Tyre("M"),
			new Tyre("M"),
			new Tyre("M")};
	
	public Tyre[] getTyres() {
		return tyres;
	}
	
	public void setTyres(Tyre[] tyres) {
		this.tyres=tyres;
	}
	
	Car(){
		
	}
	
	Car(Tyre[] tyres){
		this.tyres=tyres;
	}
	
	
	
}
