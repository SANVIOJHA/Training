package coolTrack;

public class DataCenter{

	String dataCenterId;
	String location;
	String supervisorName;
	double coolingPowerUsage;
	DataCenter(){
		
	}
	DataCenter(String dataCenterId,String location,String supervisorName,double coolingPowerUsage){
		this.dataCenterId=dataCenterId;
		this.location=location;
		this.supervisorName=supervisorName;
		this.coolingPowerUsage=coolingPowerUsage;
		
	}
	
}
