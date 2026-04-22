package mediSureDiagnostics;

public class ImagingTest extends DiagnosticTest {

    private String scanType;     // MRI / CT / XRAY
    private int scanDuration;    // minutes
    private double ratePerMinute;

    public ImagingTest() {}

    public ImagingTest(String testId, String testDate, int testPriority,
                       String scanType, int scanDuration, double ratePerMinute) {
        super(testId, testDate, testPriority);
        this.scanType = scanType;
        this.scanDuration = scanDuration;
        this.ratePerMinute = ratePerMinute;
    }
    public String getScanType() {
        return scanType;
    }
    @Override
    public String equipmentSelection() {

        if (scanDuration < 20)
            return "BasicScanner";
        else if (scanDuration <= 45)
            return "AdvancedScanner";
        else
            return "UltraScanner";
    }

    @Override
    public double calculateFinalBill() {

        // Base cost
        double baseCost = scanDuration * ratePerMinute;

        // Scan type multiplier
        if (scanType.equalsIgnoreCase("MRI"))
            baseCost *= 1.5;
        else if (scanType.equalsIgnoreCase("CT"))
            baseCost *= 1.3;
        else if (scanType.equalsIgnoreCase("XRAY"))
            baseCost *= 1.1;

        // Equipment cost
        double equipmentCost;
        String equipment = equipmentSelection();

        if (equipment.equalsIgnoreCase("BasicScanner"))
            equipmentCost = 1000;
        else if (equipment.equalsIgnoreCase("AdvancedScanner"))
            equipmentCost = 2000;
        else
            equipmentCost = 3500;

        // Tax
        double tax = baseCost * 0.25;

        // Discount based on priority
        double discount = 0;
        if (testPriority == 5)
            discount = baseCost * 0.20;
        else if (testPriority == 3 || testPriority == 4)
            discount = baseCost * 0.10;

        return (baseCost + equipmentCost + tax) - discount;
    }
}
