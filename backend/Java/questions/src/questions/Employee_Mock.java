package questions;

import java.util.*;

public class Employee_Mock {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<Integer, String> employeeMap = new HashMap<>();

        while (sc.hasNext()) {

            String command = sc.next();

            if (command.equals("A")) {
                // Add employee
                int employeeID = sc.nextInt();
                String designation = sc.next();

                employeeMap.put(employeeID, designation);
            }

            else if (command.equals("U")) {
                // Update employee designation
                int employeeID = sc.nextInt();
                String newDesignation = sc.next();

                updateDesignation(employeeMap, employeeID, newDesignation);
            }
        }

        sc.close();
    }

    public static void updateDesignation(Map<Integer, String> employeeMap,
                                         int employeeID,
                                         String newDesignation) {

        if (employeeMap.containsKey(employeeID)) {
            employeeMap.put(employeeID, newDesignation);
            System.out.println(employeeID + " " + newDesignation);
        }
    }
}
