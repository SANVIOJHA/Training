package abstraction_question2_Ride_Booking_Application;

public class BikeRide extends Ride {
	@Override
	public void calculateFare(int distance) {
		int fare=600;
		int total=distance*fare;
		System.out.println("fare for bike ride is : "+total);
	}
	
	

}
