package service.booking.exceptions;

public class NoBookingsException extends Exception {
  public NoBookingsException() {
    super("No Bookings Registered");
  }
}
