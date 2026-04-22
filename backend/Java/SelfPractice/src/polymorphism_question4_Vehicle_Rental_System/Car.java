package polymorphism_question4_Vehicle_Rental_System;

public class Car extends Vehicle{
	@Override
	void calculateRent(double rent) {
		System.out.println("Car rent is "+rent);
	}
}
