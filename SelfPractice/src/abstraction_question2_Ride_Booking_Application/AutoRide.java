package abstraction_question2_Ride_Booking_Application;

public class AutoRide extends Ride{
	@Override
	public void calculateFare(int distance) {
		int fare=500;
		int total=distance*fare;
		System.out.println("fare for auto ride is : "+total);
	}

}
