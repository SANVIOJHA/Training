package polymorphism_question4_Vehicle_Rental_System;

public class Truck extends Vehicle{
	@Override
	void calculateRent(double rent) {
		System.out.println("Truck rent is "+rent);
	}

}
