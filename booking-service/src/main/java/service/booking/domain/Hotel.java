package service.booking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Hotel {
  @Id
  private String id;

  private final String hotelId;
  private final String name;
  private final String city;
  private final int totalRooms;
  private final int availableRooms;

  public Hotel(String hotelId, String name, String city, int totalRooms) {
    this.hotelId = hotelId;
    this.name = name;
    this.city = city;
    this.totalRooms = totalRooms;
    this.availableRooms = totalRooms;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Hotel hotel = (Hotel) o;
    return totalRooms == hotel.totalRooms &&
            availableRooms == hotel.availableRooms &&
            Objects.equals(id, hotel.id) &&
            Objects.equals(hotelId, hotel.hotelId) &&
            Objects.equals(name, hotel.name) &&
            Objects.equals(city, hotel.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, hotelId, name, city, totalRooms, availableRooms);
  }

  @Override
  public String toString() {
    return "Hotel{" +
            "id='" + id + '\'' +
            ", hotelId='" + hotelId + '\'' +
            ", name='" + name + '\'' +
            ", city='" + city + '\'' +
            ", totalRooms=" + totalRooms +
            ", availableRooms=" + availableRooms +
            '}';
  }
}
