package objectOrientedEncapsulation;
//BoxMF
public class MainFactory {

	public static void main(String[] args) {
///	///	BoxMF a=new BoxMF();// constructor is private so this method is wrong
//		System.out.println();
		
		BoxMF a=BoxMF.createBox();
		System.out.println(a);
		
		

	}

}
