package operators;


public class LogicalOperator1 {

	public static void main(String[] args) {
		
		int a=15;
		//int b=20;
		int c=21;
		String res1 = (a%3==0 && a%5==0) ? "divisible by both 3 and 5" : (a%3==0 ? "divisible by 3" : (a%5==0 ? "divisible by 5" : "not divisible by 3 or 5"));

//		
		System.out.println(res1);
		
		String res2 = (c%3==0 && c%5==0) ? "divisible by both 3 and 5" : (c%3==0 ? "divisible by 3" : (c%5==0 ? "divisible by 5" : "not divisible by 3 or 5"));

//		
		System.out.println(res2);

	}

}
