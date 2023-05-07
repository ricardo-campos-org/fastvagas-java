package fastvagas.service;

import fastvagas.dto.UserCreateDto;
import fastvagas.dto.UserUpdateDto;
import fastvagas.entity.UserEntity;
import fastvagas.exception.UserExistsException;
import fastvagas.exception.UserNotFoundException;
import fastvagas.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class container methods to handle user related routines and processes. */
@Slf4j
@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

  private UserRepository userRepository;

  @Autowired
  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Creates a user in the system.
   *
   * @param userCreateDto A {@link UserCreateDto} containing all required fields.
   * @return A {@link UserEntity} created
   */
  public UserEntity createUser(UserCreateDto userCreateDto) {
    log.info("Creating user {}", userCreateDto);

    // Validations
    List<UserEntity> usersEmailList = userRepository.findAllByEmail(userCreateDto.getEmail());
    if (!usersEmailList.isEmpty()) {
      log.info("User already exists! {}", userCreateDto);
      throw new UserExistsException(userCreateDto.getEmail());
    }

    // Storage
    UserEntity user = new UserEntity();
    user.setFirstName(userCreateDto.getFirstName());
    user.setLastName(userCreateDto.getLastName());
    user.setEmail(userCreateDto.getEmail());
    user.setCity(userCreateDto.getCity());
    user.setState(userCreateDto.getState());
    user.setCreatedAt(LocalDateTime.now());
    user.setTerms(userCreateDto.getTerms());

    UserEntity userEntity = userRepository.save(user);
    log.info("User created with success! {}", userEntity);

    return userEntity;
  }

  /**
   * Updates a user in the system.
   *
   * @param userId User's identification number
   * @param userUpdateDto An instance of {@link UserUpdateDto} containing all updatable fields
   * @return A {@link UserEntity} updated
   */
  public UserEntity updateUser(long userId, UserUpdateDto userUpdateDto) {
    log.info("Updating user id {} with {}", userId, userUpdateDto);

    Optional<UserEntity> userEntityOp = userRepository.findById(userId);
    if (userEntityOp.isEmpty()) {
      throw new UserNotFoundException(userId);
    }

    UserEntity userEntity = userEntityOp.get();
    userEntity.setFirstName(userUpdateDto.getFirstName());
    userEntity.setLastName(userUpdateDto.getLastName());
    userEntity.setEmail(userUpdateDto.getEmail());
    userEntity.setTerms(userUpdateDto.getTerms());
    userEntity.setDisabledAt(null);

    UserEntity userEntitySaved = userRepository.save(userEntity);
    log.info("User updated with success! {}", userEntitySaved);

    return userEntitySaved;
  }

  /**
   * Disables a user.
   *
   * @param userId User's identification number
   */
  public void disableUser(long userId) {
    log.info("Disabling user id {}", userId);

    Optional<UserEntity> userEntityOp = userRepository.findById(userId);
    if (userEntityOp.isEmpty()) {
      throw new UserNotFoundException(userId);
    }

    UserEntity userEntity = userEntityOp.get();
    userEntity.setDisabledAt(LocalDateTime.now());

    UserEntity userEntityDisabled = userRepository.save(userEntity);
    log.info("User disabled with success! {}", userEntityDisabled);
  }

  /**
   * Get all users.
   *
   * @return A {@link List} of {@link UserEntity} or an empty list.
   */
  public List<UserEntity> getAllUsers() {
    log.info("Fetching all users.");
    return userRepository.findAll();
  }
}
