package fastvagas.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import fastvagas.dto.UserCreateDto;
import fastvagas.dto.UserUpdateDto;
import fastvagas.entity.UserEntity;
import fastvagas.exception.UserExistsException;
import fastvagas.exception.UserNotFoundException;
import fastvagas.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock UserRepository userRepository;

  private UserService userService;

  @BeforeEach
  void setup() {
    userService = new UserService(userRepository);
  }

  private UserCreateDto getCreateDto() {
    UserCreateDto createDto1 = new UserCreateDto();
    createDto1.setFirstName("First");
    createDto1.setLastName("Last");
    createDto1.setEmail("email@domain.com");
    createDto1.setCity("Joinville");
    createDto1.setState("SC");
    createDto1.setTerms("Dev,Fullstack,Backend,Frontend");
    return createDto1;
  }

  private UserUpdateDto getUpdateDto() {
    UserUpdateDto updateDto = new UserUpdateDto();
    updateDto.setFirstName("First");
    updateDto.setLastName("Last");
    updateDto.setEmail("email@domain.com");
    updateDto.setTerms("Dev,Fullstack,Backend,Frontend");
    return updateDto;
  }

  @Test
  @DisplayName("createUserDuplicatedEmailTest")
  void createUserDuplicatedEmailTest() {
    when(userRepository.findAllByEmail(any())).thenReturn(List.of(new UserEntity()));

    UserExistsException exception =
        Assertions.assertThrows(
            UserExistsException.class, () -> userService.createUser(getCreateDto()));

    Assertions.assertEquals(
        "User already exists for this email: email@domain.com", exception.getMessage());
  }

  @Test
  @DisplayName("createUserSuccessTest")
  void createUserSuccessTest() {
    LocalDateTime now = LocalDateTime.now();

    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setEmail("email@domain.com");
    user.setCity("New Jersey");
    user.setState("NJ");
    user.setCreatedAt(now);
    user.setTerms("Dev,Fullstack,Backend,Frontend");

    when(userRepository.findAllByEmail(any())).thenReturn(List.of());
    when(userRepository.save(any())).thenReturn(user);

    UserEntity userCreated = userService.createUser(getCreateDto());

    Assertions.assertEquals(1L, userCreated.getId());
    Assertions.assertEquals("First", userCreated.getFirstName());
    Assertions.assertEquals("Last", userCreated.getLastName());
    Assertions.assertEquals("email@domain.com", userCreated.getEmail());
    Assertions.assertEquals("New Jersey", userCreated.getCity());
    Assertions.assertEquals("NJ", userCreated.getState());
    Assertions.assertEquals(now, userCreated.getCreatedAt());
    Assertions.assertNull(userCreated.getDisabledAt());
    Assertions.assertEquals("Dev,Fullstack,Backend,Frontend", userCreated.getTerms());
    Assertions.assertNull(userCreated.getLastSearch());
  }

  @Test
  @DisplayName("updateUserNotFoundTest")
  void updateUserNotFoundTest() {
    when(userRepository.findById(any())).thenReturn(Optional.empty());

    UserNotFoundException exception =
        Assertions.assertThrows(
            UserNotFoundException.class, () -> userService.updateUser(1L, getUpdateDto()));

    Assertions.assertEquals("User not found for id: 1", exception.getMessage());
  }

  @Test
  @DisplayName("updateUserSuccessTest")
  void updateUserSuccessTest() {
    LocalDateTime now = LocalDateTime.now();

    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setEmail("email@domain.com");
    user.setCity("New Jersey");
    user.setState("NJ");
    user.setCreatedAt(now);
    user.setTerms("Dev,Fullstack,Backend,Frontend");

    when(userRepository.findById(any())).thenReturn(Optional.of(user));
    when(userRepository.save(any())).thenReturn(user);

    UserEntity userUpdated = userService.updateUser(1L, getUpdateDto());

    Assertions.assertEquals(1L, userUpdated.getId());
    Assertions.assertEquals("First", userUpdated.getFirstName());
    Assertions.assertEquals("Last", userUpdated.getLastName());
    Assertions.assertEquals("email@domain.com", userUpdated.getEmail());
    Assertions.assertEquals("New Jersey", userUpdated.getCity());
    Assertions.assertEquals("NJ", userUpdated.getState());
    Assertions.assertEquals(now, userUpdated.getCreatedAt());
    Assertions.assertNull(userUpdated.getDisabledAt());
    Assertions.assertEquals("Dev,Fullstack,Backend,Frontend", userUpdated.getTerms());
    Assertions.assertNull(userUpdated.getLastSearch());
  }

  @Test
  @DisplayName("disableUserSuccessTest")
  void disableUserSuccessTest() {
    LocalDateTime now = LocalDateTime.now();

    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setEmail("email@domain.com");
    user.setCity("New Jersey");
    user.setState("NJ");
    user.setCreatedAt(now);
    user.setTerms("Dev,Fullstack,Backend,Frontend");
    user.setDisabledAt(now);

    when(userRepository.findById(any())).thenReturn(Optional.of(user));
    when(userRepository.save(any())).thenReturn(user);

    userService.disableUser(1L);

    Assertions.assertEquals(1L, user.getId());
    Assertions.assertEquals("First", user.getFirstName());
    Assertions.assertEquals("Last", user.getLastName());
    Assertions.assertEquals("email@domain.com", user.getEmail());
    Assertions.assertEquals("New Jersey", user.getCity());
    Assertions.assertEquals("NJ", user.getState());
    Assertions.assertEquals(now, user.getCreatedAt());
    Assertions.assertNotNull(user.getDisabledAt());
    Assertions.assertEquals("Dev,Fullstack,Backend,Frontend", user.getTerms());
    Assertions.assertNull(user.getLastSearch());
  }

  void getAllUsers() {
    //
  }
}
