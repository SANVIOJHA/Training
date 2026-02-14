package mediSureDiagnostics;

public class BloodTest extends DiagnosticTest {

    private int sampleCount;
    private String testCategory;
    private double costPerSample;

    public BloodTest() {}

    public BloodTest(String testId, String testDate, int testPriority,
                     int sampleCount, String testCategory, double costPerSample) {
        super(testId, testDate, testPriority);
        this.sampleCount = sampleCount;
        this.testCategory = testCategory;
        this.costPerSample = costPerSample;
    }

    public String getTestCategory() {
        return testCategory;
    }

    @Override
    public String equipmentSelection() {
        if (sampleCount < 5)
            return "Analyzer";
        else if (sampleCount <= 10)
            return "AutoAnalyzer";
        else
            return "HighThroughputSystem";
    }

    @Override
    public double calculateFinalBill() {

        double baseCost = sampleCount * costPerSample;

        if (testCategory.equalsIgnoreCase("Advanced"))
            baseCost *= 1.35;

        double equipmentCost;
        String equipment = equipmentSelection();

        if (equipment.equals("Analyzer"))
            equipmentCost = 800;
        else if (equipment.equals("AutoAnalyzer"))
            equipmentCost = 1500;
        else
            equipmentCost = 2800;

        double tax = baseCost * 0.18;

        double discount = 0;
        if (testPriority == 5)
            discount = baseCost * 0.20;
        else if (testPriority == 3 || testPriority == 4)
            discount = baseCost * 0.10;

        return (baseCost + equipmentCost + tax) ;
    }
}
