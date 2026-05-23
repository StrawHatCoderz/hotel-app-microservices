package service.booking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Booking {

  @Id
  private String id;

  private String bookingId;
  private String hotelId;
  private String userId;
  private int totalRoomsBooked;
  private String receiptPath;

  public Booking() {
  }

  public Booking(
          String bookingId,
          String hotelId,
          String userId,
          int totalRoomsBooked
  ) {
    this.bookingId = bookingId;
    this.hotelId = hotelId;
    this.userId = userId;
    this.totalRoomsBooked = totalRoomsBooked;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBookingId() {
    return bookingId;
  }

  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  public String getHotelId() {
    return hotelId;
  }

  public void setHotelId(String hotelId) {
    this.hotelId = hotelId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getTotalRoomsBooked() {
    return totalRoomsBooked;
  }

  public void setTotalRoomsBooked(int totalRoomsBooked) {
    this.totalRoomsBooked = totalRoomsBooked;
  }

  public String getReceiptPath() {
    return receiptPath;
  }

  public void setReceiptPath(String receiptPath) {
    this.receiptPath = receiptPath;
  }

  public boolean isReceiptGenerated() {
    return receiptPath != null;
  }

  @Override
  public String toString() {
    return "Booking[" +
            "id=" + id +
            ", bookingId=" + bookingId +
            ", hotelId=" + hotelId +
            ", userId=" + userId +
            ", totalRoomsBooked=" + totalRoomsBooked +
            ", receiptPath=" + receiptPath +
            ']';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Booking booking = (Booking) o;

    return totalRoomsBooked == booking.totalRoomsBooked &&
            Objects.equals(id, booking.id) &&
            Objects.equals(bookingId, booking.bookingId) &&
            Objects.equals(hotelId, booking.hotelId) &&
            Objects.equals(userId, booking.userId) &&
            Objects.equals(receiptPath, booking.receiptPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            id,
            bookingId,
            hotelId,
            userId,
            totalRoomsBooked,
            receiptPath
    );
  }
}