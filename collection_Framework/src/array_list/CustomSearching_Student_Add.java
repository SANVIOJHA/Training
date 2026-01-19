package array_list;
import java.util.*;

public class CustomSearching_Student_Add {

    public static void main(String[] args) {

        ArrayList<Student_Add> st = new ArrayList<>();
        st.add(new Student_Add("abc", 1));
        st.add(new Student_Add("def", 2));
        st.add(new Student_Add("ghi", 3));
        st.add(new Student_Add("jkl", 4));

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = sc.nextInt();

        boolean found = false;

        ListIterator<Student_Add> l = st.listIterator();

        while (l.hasNext()) {
            Student_Add s = l.next();
            if (s.getId() == id) {   // custom search condition
                System.out.println("Student found: " + s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found");
        }
    }
}
