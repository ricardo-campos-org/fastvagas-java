package fastvagas.resource;

import fastvagas.dto.UserCreateDto;
import fastvagas.dto.UserUpdateDto;
import fastvagas.entity.Job;
import fastvagas.entity.UserEntity;
import fastvagas.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class holds resources to handle User in the system. */
@Validated
@RestController
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping(path = "/users", produces = "application/json")
public class UserResource {

  private UserService userService;

  @Autowired
  UserResource(UserService userService) {
    this.userService = userService;
  }

  /**
   * Creates a {@link UserEntity} in the database.
   *
   * @param userCreateDto a {@link UserCreateDto} instance
   * @return a {@link UserEntity} with the created user
   */
  @PostMapping(consumes = "application/json")
  public UserEntity createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
    return userService.createUser(userCreateDto);
  }

  /**
   * Updates a {@link UserEntity} in the database.
   *
   * @param id User's identification number
   * @param userUpdateDto a {@link UserUpdateDto}
   * @return a {@link UserEntity} with the updated user
   */
  @PutMapping(path = "/{id}", consumes = "application/json")
  public UserEntity updateUser(
      @PathVariable long id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
    return userService.updateUser(id, userUpdateDto);
  }

  @DeleteMapping(path = "/{id}")
  public void disableUser(@PathVariable long id) {
    userService.disableUser(id);
  }

  @GetMapping()
  public List<UserEntity> getAll() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}/jobs")
  public List<Job> getAllUserJobs() {
    return List.of();
  }
}
