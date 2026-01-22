package map;
import java.util.*;

public class HashMapCopied {
    public static void main(String[] args) {

        HashMap hm = new HashMap<>();

        hm.put(1, "Shraddha");
        hm.put(2, "Saddha");
        hm.put(3, "Addha");
        hm.put(4, "Sddha");
        hm.put(5, "Shr");

        System.out.println("Original Map: " + hm);
        System.out.println();

       
//           Iterator using entrySet()
        System.out.println("Iterating using entrySet():");
        Iterator<Map.Entry<Integer, String>> itr1 = hm.entrySet().iterator();

        while (itr1.hasNext()) {
            Map.Entry<Integer, String> entry = itr1.next();
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());

           
            if (entry.getKey() == 3) {
                itr1.remove();
            }
        }

        System.out.println("After removal (key 3): " + hm);
        System.out.println();

       
//           Iterator using keySet()
           
        System.out.println("Iterating using keySet():");
        Iterator<Integer> itr2 = hm.keySet().iterator();

        while (itr2.hasNext()) {
            Integer key = itr2.next();
            System.out.println("Key: " + key + " Value: " + hm.get(key));
        }

        System.out.println();

        
//            Iterator using values()
           
        System.out.println("Iterating using values():");
        Iterator<String> itr3 = hm.values().iterator();

        while (itr3.hasNext()) {
            String value = itr3.next();
            System.out.println("Value: " + value);
        }
    }
}
