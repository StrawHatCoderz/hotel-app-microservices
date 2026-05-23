package service.booking.exceptions;

public class HotelNotFoundException extends Exception {
  public HotelNotFoundException(String message) {
    super(message);
  }
}
