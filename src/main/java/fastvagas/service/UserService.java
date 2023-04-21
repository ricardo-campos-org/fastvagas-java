package fastvagas.service;
import java.time.LocalDateTime;

import fastvagas.dto.UserCreateDto;
import fastvagas.entity.UserEntity;
import fastvagas.repository.UserRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  private UserRepository userRepository;

  private UserService() {}

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
      throw new RuntimeException("User already exists!");
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
}
