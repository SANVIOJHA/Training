package mediSureDiagnostics;

public class Utility {

    // 1️⃣ Validate Test ID → MED + 3 digits + Capital Letter
    public boolean validateTestId(String testId) {

        if (testId.matches("MED[0-9]{3}[A-Z]")) {
            return true;
        } else {
            System.out.println("Test id " + testId + " is invalid");
            System.out.println("Please provide a valid record");
            return false;
        }
    }

    // 2️⃣ Parse input string & return DiagnosticTest object
    public DiagnosticTest parseDetails(String input) {

        String[] data = input.split(":");

        String testId = data[0];
        String testDate = data[1];
        int testPriority = Integer.parseInt(data[2]);
        String testType = data[3];

        // Validate Test ID first
        if (!validateTestId(testId)) {
            return null;
        }

        // BloodTest parsing
        if (testType.equalsIgnoreCase("BloodTest")) {

            int sampleCount = Integer.parseInt(data[4]);
            String testCategory = data[5];
            double costPerSample = Double.parseDouble(data[6]);

            return new BloodTest(
                    testId,
                    testDate,
                    testPriority,
                    sampleCount,
                    testCategory,
                    costPerSample
            );
        }

        // ImagingTest parsing
        else if (testType.equalsIgnoreCase("ImagingTest")) {

            String scanType = data[4];
            int scanDuration = Integer.parseInt(data[5]);
            double ratePerMinute = Double.parseDouble(data[6]);

            return new ImagingTest(
                    testId,
                    testDate,
                    testPriority,
                    scanType,
                    scanDuration,
                    ratePerMinute
            );
        }

        return null;
    }

    // 3️⃣ Identify Test Type using polymorphism
    public String findTestType(DiagnosticTest test) {

        if (test instanceof BloodTest)
            return "BloodTest";
        else if (test instanceof ImagingTest)
            return "ImagingTest";
        else
            return "Unknown Test Type";
    }
}
