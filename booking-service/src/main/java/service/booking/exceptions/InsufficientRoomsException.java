package service.booking.exceptions;

public class InsufficientRoomsException extends Exception {
  public InsufficientRoomsException() {
    super("Insufficient rooms");
  }
}
