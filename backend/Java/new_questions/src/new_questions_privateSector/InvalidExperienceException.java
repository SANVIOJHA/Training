package new_questions_privateSector;

public class InvalidExperienceException extends Exception{
	String msg;
	
	InvalidExperienceException(){
		
	}
	InvalidExperienceException(String msg){
		this.msg=msg;
	}
	
	String getInvalidExperienceException(){
		return msg;
	}
	void setInvalidExperienceException(String msg) {
		this.msg=msg;
	}

}
