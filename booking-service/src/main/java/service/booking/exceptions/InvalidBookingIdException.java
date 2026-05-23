package service.booking.exceptions;

public class InvalidBookingIdException extends Exception {
  public InvalidBookingIdException() {
    super("Invalid Booking Id");
  }
}
