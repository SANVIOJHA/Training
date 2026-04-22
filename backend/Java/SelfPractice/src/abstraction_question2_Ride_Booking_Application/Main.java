package abstraction_question2_Ride_Booking_Application;

public class Main {

	public static void main(String[] args) {
		Ride r;
		r=new BikeRide();
		r.calculateFare(20);
		System.out.println();
		r=new AutoRide();
		r.calculateFare(560);
		System.out.println();
		r=new CarRide();
		r.calculateFare(78);

	}

}
