package throw_exception_handling;
import java.util.*;
public class Vote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter age ");
		int age=sc.nextInt();
	
		if(age<18) {
			throw new UnderAgeException("under age can't vote");
		}else if(age>41) {
			throw new OverAgeException("over age can't vote");
		}else {
			System.out.println("can vote");
		}
	}

}
