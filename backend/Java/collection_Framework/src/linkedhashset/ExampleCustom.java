package linkedhashset;

import java.util.LinkedHashSet;


public class ExampleCustom {
    public static void main(String[] args) {
        LinkedHashSet<Student_Add> hs = new LinkedHashSet<>();

        hs.add(new Student_Add(1, "Amit"));
        hs.add(new Student_Add(2, "Neha"));
        hs.add(new Student_Add(1, "Amit"));
        hs.add(new Student_Add(3, "Riya"));
        hs.add(new Student_Add(1, "Amit"));
        hs.add(new Student_Add(27, "Neha"));
        hs.add(new Student_Add(14, "Amit"));
        hs.add(new Student_Add(30, "Riya"));
        System.out.println(hs);

        for (Student_Add s : hs) {
            System.out.println(s);
        }
    }
}



/*hashset output 
[3 - Riya, 2 - Neha, 1 - Amit, 14 - Amit, 30 - Riya, 27 - Neha]
3 - Riya
2 - Neha
1 - Amit
14 - Amit
30 - Riya
27 - Neha
*/
