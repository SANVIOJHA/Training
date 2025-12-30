package operators;

//8.A system allows login only if the username and password are correct and the account is not locked. 
//If the login fails, increase the login attempt counter using a compound assignment operator 
//and use a conditional operator to lock the account after a certain number of attempts.


import java.util.*;


public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		
		System.out.println("username : ");
		String name=sc.next();
		
		System.out.println("password : ");
		int pass=sc.nextInt();
		
		System.out.println("account is locked or not  : ");
		String acc=sc.next();
		
		int count=1;
		//int max=5;
		String action = (name=="sanvi" && pass==123 && acc=="no")?"logined":("failed "+(count++));
		System.out.println(action);
		
sc.close();
	}

}
