package new_questions_privateSector;

public class Asset {
	String assetId;
	String assetName;
	String assetExpiry;
	
	Asset(){
		
	}
	Asset(String assetId,String assetName,String assetExpiry){
		this.assetExpiry=assetExpiry;
		this.assetId=assetId;
		this.assetName=assetName;
		
	}
	String getAssetId() {
		return assetId;
	}
	void setAssetId(String assetId) {
		this.assetId=assetId;
	}
	String getAssetName() {
		return assetName;
	}
	void setAssetName(String assetName) {
		this.assetName=assetName;
	}
	String getAssetExpiry() {
		return assetExpiry;
	}
	void setAssetExpiry(String assetExpiry) {
		this.assetExpiry=assetExpiry;
	}
	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", assetName=" + assetName + ", assetExpiry=" + assetExpiry + "]";
	}
	
	

}
