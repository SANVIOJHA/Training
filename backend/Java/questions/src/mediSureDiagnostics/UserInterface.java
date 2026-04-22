package mediSureDiagnostics;

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Utility utility = new Utility();

        // Step 1: Read input
        System.out.println("Enter the diagnostic test details");
        String input = sc.nextLine();

        // Step 2: Parse details
        DiagnosticTest test = utility.parseDetails(input);

        // Invalid test id case
        if (test == null) {
            return;
        }

        // Step 3: Display common details
        System.out.println("Test id : " + test.getTestId());
        System.out.println("Date of test : " + test.getTestDate());
        System.out.println("Test priority : " + test.getTestPriority());

        // Step 4: Display specific test details
        String testType = utility.findTestType(test);

        if (testType.equals("BloodTest")) {
            BloodTest bt = (BloodTest) test;
            System.out.println("Test category : " + bt.getTestCategory());
        } 
        else if (testType.equals("ImagingTest")) {
            ImagingTest it = (ImagingTest) test;
            System.out.println("Scan type : " + it.getScanType());
        }

        // Step 5: Equipment & Bill
        System.out.println("Equipment used : " + test.equipmentSelection());
        System.out.println("Final bill : " + test.calculateFinalBill());

        sc.close();
    }
}
