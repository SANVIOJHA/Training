package new_questions_privateSector;

public class InvalidAssetException {
	String msg;
	
	InvalidAssetException(){
		
	}
	InvalidAssetException(String msg){
		this.msg=msg;
	}
	
	String getInvalidAssetException(){
		return msg;
	}
	void setInvalidAssetException(String msg) {
		this.msg=msg;
	}

}
