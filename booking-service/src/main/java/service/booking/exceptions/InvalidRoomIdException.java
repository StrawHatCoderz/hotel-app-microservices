package service.booking.exceptions;

public class InvalidRoomIdException extends Exception {
  public InvalidRoomIdException() {
    super("Invalid Room Id");
  }
}
