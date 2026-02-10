package streamApi;
import java.util.*;

public class Employee {

    public static void main(String[] args) {

        List<String> s = Arrays.asList("s", "d", "h", "e");
        List<String> x = Arrays.asList("7523145678", "15678991");

        Collections.sort(s);
        System.out.println(s);

        for (String n : x) {
            if (n.matches("^7\\d{9}$")) {
                System.out.println("valid " + n);
            } else {
                System.out.println("invalid " + n);
            }
        }
    }
}
