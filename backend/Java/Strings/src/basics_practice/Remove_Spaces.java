package basics_practice;

public class Remove_Spaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="hello hi byre";
//		str=str.replace(" ", "");
//		System.out.println(str);
		String r="";
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!=' ') {
				r+=str.charAt(i);
			}
		}
		System.out.println(r);

	}

}
