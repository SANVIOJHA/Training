package regularExpression_basics;

public class example {
	public static void main(String[] args) {
		String s="fghb123abc";
		/////normal logic for string this many lines is needed to overcome this regex is introduced 
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if(c>='0' && c<='9') {
				System.out.println(s);
				continue;
			}else {
				System.out.println(s);
				System.out.println("non numeric ");
				break;
			}
		}
	}

}
