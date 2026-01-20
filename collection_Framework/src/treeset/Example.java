package treeset;
import java.util.*;

public class Example {
    public static void main(String[] args) {

        // 1. Creating TreeSet
        TreeSet<Integer> l = new TreeSet<>();

        // 2. Adding elements
        l.add(10);
        l.add(11);
        l.add(12);
        l.add(13);
        l.add(2);
        l.add(130);
        l.add(122);
        l.add(1);

        // 3. Printing TreeSet (sorted order)
        System.out.println("TreeSet: " + l);

        // 4. Size
        System.out.println("Size: " + l.size());

        // 5. Contains
        System.out.println("Contains 11: " + l.contains(11));
        System.out.println("Contains 50: " + l.contains(50));

        // 6. First & Last
        System.out.println("First: " + l.first());
        System.out.println("Last: " + l.last());

        // 7. Higher & Lower
        System.out.println("Higher than 11: " + l.higher(11));
        System.out.println("Lower than 11: " + l.lower(11));

        // 8. Ceiling & Floor
        System.out.println("Ceiling of 11: " + l.ceiling(11));
        System.out.println("Floor of 11: " + l.floor(11));

        // 9. SubSet, HeadSet, TailSet
        System.out.println("SubSet (10 to 130): " + l.subSet(10, 130));
        System.out.println("HeadSet (<12): " + l.headSet(12));
        System.out.println("TailSet (>=12): " + l.tailSet(12));

        // 10. Traversing (for-each)
        System.out.println("Traversal:");
        for (Integer i : l) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 11. Descending order
        System.out.println("Descending order:");
        Iterator<Integer> it = l.descendingIterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 12. Poll operations
        System.out.println("Poll First: " + l.pollFirst());
        System.out.println("Poll Last: " + l.pollLast());

        // 13. After polling
        System.out.println("After Polling: " + l);

        // 14. Remove element
        l.remove(12);
        System.out.println("After removing 12: " + l);

        // 15. Clear TreeSet
        l.clear();
        System.out.println("After clear: " + l);
    }
}
