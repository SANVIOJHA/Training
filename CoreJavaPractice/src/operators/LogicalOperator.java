package operators;


public class LogicalOperator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=15;
		int b=20;
		int c=21;
		String res1=(a%3==0 &&a%5==0)?"divisible by both":"not divisible by both";
		String res2=(b%2==0 &&b%5==0)?"divisible by both":"not divisible by both";
		String res3=(c%3==0 &&c%7==0)?"divisible by both":"not divisible by both";
		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);

	}

}
