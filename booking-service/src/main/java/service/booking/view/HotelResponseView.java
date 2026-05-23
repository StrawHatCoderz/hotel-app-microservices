package service.booking.view;

import service.booking.domain.Hotel;

import java.util.Optional;

public record HotelResponseView(boolean success, Optional<Hotel> hotel,
                                Optional<ApiError> error) {
}
