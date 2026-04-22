package has_A_Relationship__lazyINstantiation;

public class Car {
	Tyre[] tyres=new Tyre[4];
	int i=0;
	
	////  creating this so that whenever we need a tyre we will call it in main 
	void addTyre(Tyre tyre) {
		tyres[i]=tyre;
		i++;
	}
}
