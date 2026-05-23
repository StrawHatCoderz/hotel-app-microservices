package service.booking.workers;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import service.booking.domain.Booking;
import service.booking.repositry.mongo.BookingRepo;
import service.booking.service.PDFService;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PDFWorker {

  private final StringRedisTemplate redisTemplate;
  private final BookingRepo bookingRepo;
  private final PDFService pdfService;
  private final ExecutorService executor;

  public PDFWorker(StringRedisTemplate redisTemplate, BookingRepo bookingRepo, PDFService pdfService) {
    this.redisTemplate = redisTemplate;
    this.bookingRepo = bookingRepo;
    this.pdfService = pdfService;
    this.executor = Executors.newSingleThreadExecutor();
  }

  @PostConstruct
  public void startWorker() {
    executor.submit(() -> {
      while (true) {
        try {
          String bookingId =
                  redisTemplate.opsForList()
                          .leftPop("booking-pdf-queue",
                                  Duration.ofSeconds(5));
          if (bookingId != null) {
            processBooking(bookingId);
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void processBooking(String bookingId) throws Exception {
    Booking booking = bookingRepo.findBookingByBookingId(bookingId);
    String pdfPath = pdfService.generatePdf(bookingId);
    booking.setReceiptPath(pdfPath);

    bookingRepo.save(booking);
  }
}