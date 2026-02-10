package regularExpression_basics;

public class RegExAlpha {
	public static void main(String[] args) {
		String s="abc";
		System.out.println(s.matches("[a-z]+"));
		System.out.println();
		
		String st="ABC";
		System.out.println(st.matches("[A-Z]+"));
		
		System.out.println();
		
		String str="ABCdef";
		System.out.println(str.matches("^[a-zA-Z]+$"));
		
		System.out.println();
		
		String ss="abc@gmail.com";
		System.out.println(ss.matches("^[a-zA-Z0-9._]+@[a-zA-Z ]+\\.[a-zA-Z]{2,}$"));
	}
}
