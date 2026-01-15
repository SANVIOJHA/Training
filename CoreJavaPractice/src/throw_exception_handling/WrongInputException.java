package throw_exception_handling;

public class WrongInputException extends RuntimeException{
	String msg;
	WrongInputException(){
		
	}
	WrongInputException(String msg){
		this.msg=msg;
	}
	public String getMessage() {
		return msg;
	}

}
