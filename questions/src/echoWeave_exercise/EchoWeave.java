package echoWeave_exercise;
import java.util.*;


public class EchoWeave {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the input");
        String input = sc.nextLine();

        // 1. Input Validation
        if (!input.matches("[a-zA-Z]+")) {
            System.out.println(input + " is an invalid input");
            return;
        }

        // 2. Normalize input
        String str = input.toUpperCase();

        // 3. Frequency count (preserving order)
//        why not hashmap?----because it does not keep insertion order
        
        LinkedHashMap<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        StringBuilder encrypted = new StringBuilder();

        // 4. Group 1 – ---- Even frequency characters
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                encrypted.append(entry.getKey());
            }
        }

        // 5. Group 2 – ------ Odd frequency characters
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                encrypted.append(entry.getKey());
            }
        }

        // 6. Append remaining occurrences
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            int remaining = entry.getValue() - 1;
            for (int i = 0; i < remaining; i++) {
                encrypted.append(entry.getKey());
            }
        }

        // 7. Count characters that appear exactly once
        int singleCount = 0;
        for (int count : freqMap.values()) {
            if (count == 1) {
                singleCount++;
            }
        }

        // 8. Insert count at center if needed
        if (singleCount > 0) {
            int len = encrypted.length();
            int index;

            if (len % 2 == 0) {
                index = len / 2 - 1;
            } else {
                index = len / 2;
            }

            encrypted.insert(index + 1, singleCount);
        }

        // Final Output
        System.out.println("Encrypted output: " + encrypted);
        
        sc.close();
    }
}
