package service.booking.controllers;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.booking.exceptions.*;
import service.booking.view.HotelBookingView;
import service.booking.domain.Booking;
import service.booking.service.BookingService;
import service.booking.view.ApiError;
import service.booking.view.ApiResponse;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
  private final BookingService bookingService;

  public BookingsController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @GetMapping("/")
  public ResponseEntity<ApiResponse<?>> serveBookings() {
    String currentUser = "1";
    try {
      List<Booking> hotels = bookingService.findBookingsOf(currentUser);
      return ResponseEntity.ok(ApiResponse.success(hotels));
    } catch (NoBookingsException e) {
      return ResponseEntity.ok(ApiResponse.error(new ApiError(e.getMessage())));
    }
  }

  @PostMapping("/")
  public ResponseEntity<ApiResponse<?>> bookHotel(@RequestBody HotelBookingView hotelBookingView) {
    String currentUser = "1";
    try {
      bookingService.bookRoom(currentUser, hotelBookingView.hotel_id(),
              hotelBookingView.rooms());
    } catch (UpdationFailedException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ApiResponse.error(new ApiError(e.getMessage())));
    }
    return ResponseEntity.ok(ApiResponse.success("Room Booked Successfully"));
  }

  @GetMapping("/{bookingId}/receipt.pdf")
  public ResponseEntity<?> generatePDF(@PathVariable String bookingId) {

    try {
      UrlResource pdf = bookingService.toPDF(bookingId);
      return ResponseEntity.ok()
              .contentType(MediaType.APPLICATION_PDF)
              .body(pdf);

    } catch (HotelNotFoundException | InvalidBookingIdException |
             ReceiptNotGeneratedException | MalformedURLException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ApiResponse.error(new ApiError(e.getMessage())));
    }

  }
}
