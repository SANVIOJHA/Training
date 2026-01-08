package polymorphism_question4_Vehicle_Rental_System;

public class Main {

	public static void main(String[] args) {
		Vehicle v;
		v=new Car();
		v.calculateRent(674747);
		v=new Bike();
		v.calculateRent(545454);
		v=new Truck();
		v.calculateRent(26654);
	}
}
