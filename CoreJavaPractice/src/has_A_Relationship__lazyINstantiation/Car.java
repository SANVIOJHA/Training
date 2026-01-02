package has_A_Relationship__lazyINstantiation;

public class Car {
	Tyre[] tyres=new Tyre[4];
	int i=0;
	void addTyre(Tyre tyre) {
		tyres[i]=tyre;
		i++;
	}
}
