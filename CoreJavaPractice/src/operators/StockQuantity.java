package operators;

//7.A store checks if the stock quantity is less than 10 or the item is marked as fast-moving,
//but not discontinued. Use a conditional operator to decide whether to reorder and 
//a compound assignment operator to increase the stock quantity.




import java.util.*;


public class StockQuantity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);

		
		System.out.println("stock quantity : ");
		int stock=sc.nextInt();
		
		
		System.out.println("item is : fast?");
		String item=sc.next();
		
		
		System.out.println("continue==y or discontinue==n  : ");
		String isCont=sc.next();
	
		
		String action = ((stock < 10 || item=="yes")&& isCont!="n")?("order "+"stock quantity increase "+(stock+10)):"no  ";
		System.out.println(action);
		
		sc.close();

	}

}
