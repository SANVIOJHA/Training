package basics_practice;

public class Reverse_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="hello";
		//System.out.println(str.reverse());
		for(int i=str.length()-1;i>=0;i--) {
			System.out.print(str.charAt(i));
		}
	}

}
