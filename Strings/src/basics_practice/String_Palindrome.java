package basics_practice;

public class String_Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="naan";
		String ori=s;
		String s1="helo";
		String rev="";
		for(int i=s.length()-1;i>=0;i--) {
			rev+=s.charAt(i);
		}
		ori=s;
		
		if(ori.equals(rev)) {
			System.out.println("rev is "+rev+" ori is "+ori);
		}else {
		System.out.println("false");
		}
	}

}
