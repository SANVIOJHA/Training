package new_questions_privateSector;
import java.util.ArrayList;
public class PermanentEmployee extends Employee {
	double basicPay;
	ArrayList<String> salaryComponents;
	float experience;
	ArrayList<Asset> assets;
	
	PermanentEmployee(){
		
	}
	PermanentEmployee(String employeeName,double basicPay,ArrayList<String> salaryComponents,ArrayList<Asset> assets){
		super(employeeName);
		this.basicPay=basicPay;
		this.salaryComponents=salaryComponents;
		this.assets=assets;
		
	}
	
//	bonus
	
	double calculateBonus(float experience)throws InvalidExperienceException{
		//long bonus;
		if(experience>=2.5 && experience<4) {
			return 2550;
		}else if(experience>=4 && experience<8) {
			return 5000;
		}else if(experience>=8 && experience<12) {
			return 8570;
		}if(experience>=12) {
			return 13000;
		}else {
			throw new  InvalidExperienceException ("A minimum of 2.5 years is required for bonus!");
		}
	}
	
//	salary  // 
	
	@Override
	void calculateSalary(float experience) {
		this.experience=experience;
		double da=0;
		double hra=0;
		double bonus=0;
		for(String x : salaryComponents) {
			
		}
		
		salary=basicPay+da+hra+bonus;
		
	}
	
	ArrayList<Asset> getAssetByDate(String lastDate){
		return lastDate;
	}
	
	double getBasicPay() {
		return basicPay;
	}
	void setBasicPay(double basicPay) {
		this.basicPay=basicPay;
	}
	
	ArrayList<String> getSalaryComponents(){
		return  salaryComponents;
	}
	void setSalaryComponents(ArrayList<String> salaryComponents) {
		this.salaryComponents=salaryComponents;
	}
	
	float getExperience() {
		return experience;
	}
	
	void setExperience(float experience) {
		this.experience=experience;
	}
	
	ArrayList<Asset> getAssets(){
		return assets;
	}
	
	void setAssets(ArrayList<Asset> assets) {
		this.assets=assets;
	}
	@Override
	public String toString() {
		return "PermanentEmployee [basicPay=" + basicPay + ", salaryComponents=" + salaryComponents + ", experience="
				+ experience + ", assets=" + assets + "]";
	}
	
	
	

}
