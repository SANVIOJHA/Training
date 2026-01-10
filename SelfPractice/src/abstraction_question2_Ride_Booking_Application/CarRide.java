package abstraction_question2_Ride_Booking_Application;

public  class CarRide extends Ride {
	@Override
	public void calculateFare(int distance) {
		int fare=700;
		int total=distance*fare;
		System.out.println("fare for car ride is : "+total);
	}
	


}
