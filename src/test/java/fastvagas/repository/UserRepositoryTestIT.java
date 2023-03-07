package fastvagas.repository;

import fastvagas.entity.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTestIT {

  @Autowired private UserRepository userRepository;

  private User createUser() {
    User user = new User();
    user.setId(1L);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setEmail("first.last@email.com");
    user.setCity("Joinville");
    user.setState("SC");
    user.setCreatedAt(LocalDateTime.now());
    user.setDisabledAt(null);
    user.setTerms("One, Two");
    user.setLastSearch(null);
    return user;
  }

  @Test
  @DisplayName("createTest")
  @Order(1)
  void createTest() {
    User userDb = userRepository.save(createUser());

    Assertions.assertNotNull(userDb);
    Assertions.assertEquals("First", userDb.getFirstName());
    Assertions.assertEquals("Last", userDb.getLastName());
    Assertions.assertEquals("first.last@email.com", userDb.getEmail());
    Assertions.assertEquals("Joinville", userDb.getCity());
    Assertions.assertEquals("SC", userDb.getState());
    Assertions.assertNull(userDb.getDisabledAt());
    Assertions.assertEquals("One, Two", userDb.getTerms());
    Assertions.assertNull(userDb.getLastSearch());
  }

  @Test
  @DisplayName("updateTest")
  @Order(2)
  void updateTest() {
    User userDb = userRepository.save(createUser());

    userDb.setEmail("new.address@email.com");

    User userDbSaved = userRepository.save(userDb);

    Assertions.assertNotNull(userDbSaved);
    Assertions.assertEquals(userDb.getId(), userDbSaved.getId());
    Assertions.assertEquals("new.address@email.com", userDbSaved.getEmail());
  }
}
