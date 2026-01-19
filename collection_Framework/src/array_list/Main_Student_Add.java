package array_list;
import java.util.*;

public class Main_Student_Add {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList st=new ArrayList();
		st.add(new Student_Add("abc",1));
		st.add(new Student_Add("def",2));
		st.add(new Student_Add("ghi",3));
		st.add(new Student_Add("jkl",4));
		System.out.println(st);
		
		//custom object searching
		Object k=new Student_Add("abc",1);
		System.out.println(st.contains(k));
		
		///

	}

}
