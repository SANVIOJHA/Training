package oop;

public class AllCon {
	//instance variable
	String name;
	int age;
	String clg;
	
	public AllCon(){
	
	}
	
	AllCon(String n,int a,String c){
		this.name=n;
		this.age=a;
		this.clg=c;
	}
	
	AllCon(String n,int a){
		this.name=n;
		this.age=a;
	}
	
	
	void display() {
		System.out.println("name  :  "+name+", age  :  "+age+", clg  : "+clg);
	}
	
	
	public static void main(String[] args) {
	AllCon s1=new AllCon("nfejnf",43,"dfdfdf");
	AllCon s2=new AllCon("stuf",56,"college");
	
	s1.display();
	s2.display();
		

	}

}
