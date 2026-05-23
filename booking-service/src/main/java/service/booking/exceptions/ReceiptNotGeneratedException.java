package service.booking.exceptions;

public class ReceiptNotGeneratedException extends Exception {
  public ReceiptNotGeneratedException() {
    super("Receipt is generating...");
  }
}
