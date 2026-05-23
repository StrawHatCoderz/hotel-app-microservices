package service.booking.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import service.booking.domain.Hotel;
import service.booking.view.HotelResponseView;
import service.booking.view.RoomAvailabilityUpdationView;

@Service
public class HotelService {

  private final RestClient restClient;

  public HotelService(RestClient.Builder restClientBuilder,
                      @Value("${service.hotel.uri}") String hotelServiceUrl) {
    this.restClient = restClientBuilder.baseUrl(hotelServiceUrl).build();
  }

  public HotelResponseView findHotelByHotelId(String hotelId) {
    return restClient.get()
            .uri(String.format("/hotel/%s", hotelId))
            .retrieve()
            .onStatus(HttpStatusCode::isError, ((request, response) -> {}))
            .body(HotelResponseView.class);
  }

  public RoomAvailabilityUpdationView updateAvailability(String hotelId, int roomsBooked) {
    return restClient.post()
            .uri(String.format("/hotel/update/%s", hotelId))
            .body(roomsBooked)
            .retrieve()
            .onStatus(HttpStatusCode::isError, ((request, response) -> {}))
            .body(RoomAvailabilityUpdationView.class);
  }
}
