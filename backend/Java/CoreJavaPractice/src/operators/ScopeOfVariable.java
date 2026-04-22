package operators;


public class ScopeOfVariable {

	public static void main(String[] args) {
		int a=50;
		System.out.println(a);
		{
			//int a=5;//variable is already defined
			a=90;
			int b=676;
			System.out.println(a);
			System.out.println(b);
		}
		//int a=55;
		System.out.println(a);
		//System.out.println(b);///can access only in same block/scope
		

	}

}
