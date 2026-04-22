package object_class_toString;

public class Phone {
	int ram;
	int rom;
	Phone(int ram,int rom){
		this.ram=ram;
		this.rom=rom;
	}
	public String toString() {
		return "Phone Details: Ram = " + ram + ", Rom = " + rom;
			  
		
	}
}
