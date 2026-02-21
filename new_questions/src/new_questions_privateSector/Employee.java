package new_questions_privateSector;

public abstract class Employee {
	String employeeId;
	String employeeName;
	double salary;
	static int _contractIdCounter;
	static int _permanentIdCounter;

	Employee(){
		
	}
	
	Employee(String employeeName){
		this.employeeName=employeeName;
		
	}
	
	
	abstract void  calculateSalary(float salaryFactor) ;
	
	String getEmployeeId() {
		return employeeId;
	}
	void setEmployeeId(String employeeId) {
		this.employeeId=employeeId;
	}
	
	String getEmployeeName() {
		return employeeId;
	}
	void setEmployeeName(String employeeName) {
		this.employeeName=employeeName;
	}
	
	double getsalary() {
		return salary;
	}
	void setsalary(double salary) {
		this.salary=salary;
	}
	
	static int _getPermanentIdCounter() {
		return _permanentIdCounter;
	}
	static void _setPermanentIdCounter(int permanentIdCounter) {
		permanentIdCounter=_permanentIdCounter;
	}
	
	static int _getContractIdCounter() {
		return _contractIdCounter;
	}
	static void _setContractIdCounter(int contractIdCounter) {
		contractIdCounter=_contractIdCounter;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", salary=" + salary
				+ ", _contractIdCounter=" + _contractIdCounter + ", _permanentIdCounter=" + _permanentIdCounter + "]";
	}
	
	
}
