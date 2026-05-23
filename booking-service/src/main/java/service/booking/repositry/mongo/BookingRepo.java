package service.booking.repositry.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import service.booking.domain.Booking;

import java.util.List;

@Repository
public interface BookingRepo extends MongoRepository<Booking, String> {
  List<Booking> findByUserId(String userId);

  Booking findBookingByBookingId(String bookingId);
}
