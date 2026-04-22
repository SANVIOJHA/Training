package basics_practice;

public class String_array {

public static void main(String[] args) {
		
		
		String s1=new String("hello");
		char[] c=s1.toCharArray();
		System.out.println(c);
		System.out.println();
		
		String ss="hii hello bye";
		String[] str=ss.split(" ");
		System.out.println(str);
		
		for(int i=0;i<str.length;i++) {
			System.out.println(str[i]);
		}
		
		//concat
		
		String a="hihih";
		String b="tyuiop";
		System.out.println(a.concat(b));
		
		//substring
		System.out.println(s1.substring(2));
		
		//substring (start,end)
		System.out.println(s1.substring(2, 4));
		
		System.out.println(s1.hashCode());
		System.out.println(s1.getClass());
		
		
		
		
		
		
}
String s1=new String("hello");
public int hashCode() {
	return this.s1.hashCode();
	
}


}


