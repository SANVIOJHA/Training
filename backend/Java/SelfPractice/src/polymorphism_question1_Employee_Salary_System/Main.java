package polymorphism_question1_Employee_Salary_System;

public class Main {
	public static void main(String[] args) {
		Employee e=new Developer();
		Employee e1=new Manager();
		Employee e2=new Intern();
		
		e.calculateSalary();
		e1.calculateSalary();
		e2.calculateSalary();
	}
}
