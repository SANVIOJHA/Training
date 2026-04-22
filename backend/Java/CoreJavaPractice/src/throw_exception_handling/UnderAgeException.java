package throw_exception_handling;

public class UnderAgeException extends RuntimeException{
	String msg;
	UnderAgeException(){
		
	}
	UnderAgeException(String msg){
		this.msg=msg;
		
		
	}
	public String getMessage() {
		return msg;
	}

}
