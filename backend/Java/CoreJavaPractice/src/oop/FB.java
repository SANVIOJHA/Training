package oop;

public class FB {
	 String username;
	 String pwd;
	 String email;

	 public FB(String a,String b,String c) {
		 this.username=a;
		 this.pwd=b;
		 this.email=c;
	 }
	 
	 
	 
	 
	 
	public static void main(String srgs[]) {
		FB user=new FB("om","123433","om@gmail.com");
		System.out.println("username: "+user.username);
		System.out.println("password: "+user.pwd);
		System.out.println("email: "+user.email);
		
		
	}

}