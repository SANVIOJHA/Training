package parsing_technique;
import java.util.*;
public class Try_catch {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter string");
		// TODO Auto-generated method stub
		String s=sc.nextLine();
		//System.out.println("it is string "+s);
		
		try {
		int i=Integer.parseInt(s);
		System.out.println("it is int "+i);
		}
		catch(NumberFormatException e) {
			System.out.println("it is not int ");
		}
		////
		try {
		byte b=Byte.parseByte(s);
		System.out.println("it is byte "+b);
		}
		catch(NumberFormatException e) {
			System.out.println("it is not byte");
		}
		//////
		/// 
		try {
		short sh=Short.parseShort(s);
		System.out.println("it is short "+sh);
		}
		catch(NumberFormatException e) {
			System.out.println("it is not short ");
		}
		
		try {
		double d=Double.parseDouble(s);
		System.out.println("it is Double "+d);
		}
		catch(NumberFormatException e) {
			System.out.println("it is not doub le ");
		}
		
		try {
		long l=Long.parseLong(s);
		System.out.println("it is Long "+l);
		}
		catch(NumberFormatException e) {
			System.out.println("it is not long ");
		}

}
}