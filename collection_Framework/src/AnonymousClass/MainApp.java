package AnonymousClass;
import java.util.*;
public class MainApp {
	public static void main(String[] args) {
		Student s=new Student("kk") {
			///anonymous class ///
		
			public void display() {
				System.out.println("heeeeeeee");
			}
		};
		s.display();
		
	}
}
