package hashset;

import java.util.HashSet;

import linkedhashset.Student_Add;


public class ExampleCustom {
    public static void main(String[] args) {
        HashSet<Student_Add> hs = new HashSet<>();

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
