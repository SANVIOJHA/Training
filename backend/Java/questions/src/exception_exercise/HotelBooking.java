package exception_exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class HotelBooking {

    static List<String> hotelList =
            Arrays.asList("Hilton", "Hyatt", "Marriott");

    //  USER VALIDATION 
    public static void validateUserDetails(int age, String email, String creditCard)
            throws InvalidBookingDetailsException {

        if (age < 18 || age > 100) {
            throw new InvalidBookingDetailsException("Invalid age");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidBookingDetailsException("Invalid email");
        }

        if (!creditCard.matches("\\d{16}")) {
            throw new InvalidBookingDetailsException("Invalid credit card number");
        }
    }

    //  BOOKING VALIDATION 
    public static void validateBookingDetails(String hotelName,
                                              String roomType,
                                              String checkIn,
                                              String checkOut)
            throws InvalidBookingDetailsException {

        if (!hotelList.contains(hotelName)) {
            throw new InvalidBookingDetailsException("Invalid hotel name");
        }

        if (!(roomType.equals("Standard") ||
              roomType.equals("Deluxe") ||
              roomType.equals("Suite"))) {
            throw new InvalidBookingDetailsException("Invalid room type");
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate checkInDate;
        LocalDate checkOutDate;

        try {
            checkInDate = LocalDate.parse(checkIn, formatter);
            checkOutDate = LocalDate.parse(checkOut, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidBookingDetailsException("Invalid date format");
        }

        // Allow past dates EXCEPT check-out before check-in
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new InvalidBookingDetailsException("Invalid check-out date");
        }
    }

    //COST CALCULATION (FIXED)
    public static double calculateBookingCost(String roomType) {

        if (roomType.equals("Standard")) {
            return 1000.00;
        } else if (roomType.equals("Deluxe")) {
            return 2000.00;
        } else if (roomType.equals("Suite")) {
            return 3000.00;
        }

        return 0.0;
    }
}
