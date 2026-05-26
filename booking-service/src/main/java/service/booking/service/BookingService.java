package service.booking.service;

import org.springframework.core.io.UrlResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import service.booking.domain.Booking;
import service.booking.exceptions.*;
import service.booking.repositry.mongo.BookingRepo;
import service.booking.view.RoomAvailabilityUpdationView;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
  private final BookingRepo bookingRepo;
  private final HotelService hotelService;
  private final UserService userService;
  private final StringRedisTemplate redisTemplate;

  public BookingService(BookingRepo bookingRepo,
                        HotelService hotelService,
                        UserService userService, StringRedisTemplate redisTemplate) {
    this.bookingRepo = bookingRepo;
    this.hotelService = hotelService;
    this.userService = userService;
    this.redisTemplate = redisTemplate;
  }

  public List<Booking> findBookingsOf(String currentUserId)
          throws NoBookingsException {

    List<Booking> bookings = bookingRepo.findByUserId(currentUserId);

    if (bookings.isEmpty()) throw new NoBookingsException();

    return bookings;
  }

  public void bookRoom(String currentUser, String hotelId, int noOfRooms)
          throws UpdationFailedException {
    RoomAvailabilityUpdationView result =
            hotelService.updateAvailability(hotelId,
                    noOfRooms);

    if (!result.success() && result.error().isPresent()) {
      throw new UpdationFailedException(result.error().get().message());
    }

    String id = UUID.randomUUID().toString();

    bookingRepo.save(new Booking(id, hotelId, currentUser, noOfRooms));
    redisTemplate.opsForList().rightPush("booking-pdf-queue", id);
  }

  public UrlResource toPDF(String bookingId) throws HotelNotFoundException, InvalidBookingIdException, ReceiptNotGeneratedException, MalformedURLException {
    Booking booking = bookingRepo.findBookingByBookingId(bookingId);

    if (booking == null) {
      throw new InvalidBookingIdException();s
    }

    if (!booking.isReceiptGenerated()) {
      throw new ReceiptNotGeneratedException();
    }

    Path receiptPath = Paths.get(booking.getReceiptPath());
    return new UrlResource(receiptPath.toUri());
  }
}
