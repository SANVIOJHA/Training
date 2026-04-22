package mediSureDiagnostics;

public abstract class DiagnosticTest {

    protected String testId;
    protected String testDate;
    protected int testPriority;

    public DiagnosticTest() {}

    public DiagnosticTest(String testId, String testDate, int testPriority) {
        this.testId = testId;
        this.testDate = testDate;
        this.testPriority = testPriority;
    }

    public String getTestId() {
        return testId;
    }

    public String getTestDate() {
        return testDate;
    }

    public int getTestPriority() {
        return testPriority;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public void setTestPriority(int testPriority) {
        this.testPriority = testPriority;
    }

    public abstract String equipmentSelection();
    public abstract double calculateFinalBill();
}
