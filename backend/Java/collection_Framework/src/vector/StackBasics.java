package vector;
import java.util.*;
public class StackBasics {
	public static void main(String[] args) {
		Stack s=new Stack();
		s.push(10);
		s.push(20);
		s.push(0);
		s.push(50);
		s.push(410);
		
		System.out.println(s);
		//peek
		System.out.println("last element of stack is "+ s.peek());
		
		//push
		System.out.println("adding element in the stack is "+s.push(90));
		System.out.println(s);
		//pop
		System.out.println("removing element in the stack  "+s.pop());
		System.out.println(s);
		
	}

	
}
