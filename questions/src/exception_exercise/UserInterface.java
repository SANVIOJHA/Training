package exception_exercise;

///To validate user and hotel booking details and calculate the total booking cost. Invalid inputs are handled using custom exceptions.///

///User enters details → validations are performed → exceptions handle invalid input → valid input leads to cost calculation → result is displayed.
//
//Input → Validate → Stop if error → Else continue → Calculate → Print result

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Name");
            String name = sc.nextLine();

            System.out.println("Enter Age");
            int age = Integer.parseInt(sc.nextLine());

            System.out.println("Enter Email");
            String email = sc.nextLine();

            System.out.println("Enter Credit Card Number");
            String creditCard = sc.nextLine();

            HotelBooking.validateUserDetails(age, email, creditCard);

            System.out.println("Enter Hotel Name");
            String hotelName = sc.nextLine();

            System.out.println("Select Room Type");
            String roomType = sc.nextLine();

            System.out.println("Enter Check-in Date ---->(yyyy-MM-dd)");
            String checkIn = sc.nextLine();

            System.out.println("Enter Check-out Date ---->(yyyy-MM-dd)");
            String checkOut = sc.nextLine();

            HotelBooking.validateBookingDetails(
                    hotelName, roomType, checkIn, checkOut);

            double cost = HotelBooking.calculateBookingCost(roomType);

            System.out.println("Total Booking Cost: $" + cost);

        } catch (InvalidBookingDetailsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        sc.close();
    }
}
