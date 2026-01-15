package basics_practice;

public class String_countWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="hello world bye";
		int count=0;
		for(int i=0;i<str.length();i++) {
			str.split(" ");
			count++;
		}
		System.out.println(count);

	}

}
