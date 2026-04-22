package throw_exception_handling;

public class OverAgeException extends RuntimeException{
	String msg;
	OverAgeException(){
		
	}
	OverAgeException(String msg){
		this.msg=msg;
		
		
	}
	public String getMessage() {
		return msg;
	}

}