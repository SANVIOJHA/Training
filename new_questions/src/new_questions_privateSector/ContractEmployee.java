package new_questions_privateSector;

public class ContractEmployee extends Employee {
	double wagePerHour;
	
	ContractEmployee(){
		
	}
	ContractEmployee(String employeeName,double wagePerHour){
		super(employeeName);
		this.wagePerHour=wagePerHour;
	}
	@Override
	void calculateSalary(float hoursWorked){
		salary=wagePerHour*hoursWorked;
	 if (hoursWorked<190) {
			salary=salary-(wagePerHour/2)*(190-hoursWorked);
		}
	  salary = Math.round(salary);
	}
	
	double getWagePerHour() {
		return wagePerHour;
	}
	void setWagePerHour(double wagePerHour) {
		this.wagePerHour=wagePerHour;
	}
	@Override
	public String toString() {
		return "ContractEmployee [wagePerHour=" + wagePerHour + "]";
	}
	

}
