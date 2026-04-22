package map;
import java.util.*;

public class TreeMapEx {
    public static void main(String[] args) {

        TreeMap<Integer, String> tm = new TreeMap<>();

        tm.put(3, "Java");
        tm.put(1, "Python");
        tm.put(5, "Spring");
        tm.put(2, "React");
        tm.put(4, "C++");

        System.out.println(tm);

        Iterator<Map.Entry<Integer, String>> itr1 = tm.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry<Integer, String	> e = itr1.next();
            System.out.println(e.getKey() + " " + e.getValue());
            if (e.getKey() == 2) {
                itr1.remove();
            }
        }

        System.out.println(tm);

        Iterator<Integer> itr2 = tm.keySet().iterator();
        while (itr2.hasNext()) {
            Integer k = itr2.next();
            System.out.println(k + " " + tm.get(k));
        }

        Iterator<String> itr3 = tm.values().iterator();
        while (itr3.hasNext()) {
            System.out.println(itr3.next());
        }
    }
}
