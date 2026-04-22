package object_class_toString_Array_Example;

public class Phone_A {
	int ram;
	int rom;
	Phone_A(int ram,int rom){
		this.ram=ram;
		this.rom=rom;
	}
	public String toString() {
		return "Phone Details: Ram = " + ram + ", Rom = " + rom;
			

}
}
