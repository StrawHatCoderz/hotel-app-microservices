package service.booking.domain;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class User {
  @Id
  private String id;

  private final String userId;
  private final String username;
  private final String password;

  public User(String userId, String username, String password) {
    this.userId = userId;
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public @Nullable String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", userId='" + userId + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, username, password);
  }
}
