package map;
import java.util.*;

public class LinkedHashMapIteratorEx {
    public static void main(String[] args) {

        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        lhm.put(1, "Java");
        lhm.put(2, "Python");
        lhm.put(3, "C++");
        lhm.put(4, "Spring");
        lhm.put(5, "React");

        System.out.println(lhm);

        Iterator<Map.Entry<Integer, String>> itr1 = lhm.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry<Integer, String> e = itr1.next();
            System.out.println(e.getKey() + " " + e.getValue());
            if (e.getKey() == 3) {
                itr1.remove();
            }
        }

        System.out.println(lhm);

        Iterator<Integer> itr2 = lhm.keySet().iterator();
        while (itr2.hasNext()) {
            Integer k = itr2.next();
            System.out.println(k + " " + lhm.get(k));
        }

        Iterator<String> itr3 = lhm.values().iterator();
        while (itr3.hasNext()) {
            System.out.println(itr3.next());
        }
    }
}
