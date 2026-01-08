package polymorphism_question4_Vehicle_Rental_System;

public class Bike extends Vehicle{
	@Override
	void calculateRent(double rent) {
		System.out.println("Bike rent is "+rent);
	}

}
