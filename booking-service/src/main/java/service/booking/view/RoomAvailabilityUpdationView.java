package service.booking.view;

import java.util.Optional;

public record RoomAvailabilityUpdationView(boolean success,
                                           Optional<ApiError> error) {}
