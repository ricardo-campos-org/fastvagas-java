package fastvagas.resource;

import fastvagas.dto.UserCreateDto;
import fastvagas.entity.Job;
import fastvagas.entity.UserEntity;
import fastvagas.repository.UserRepository;
import fastvagas.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/users", produces = "application/json")
public class UserResource {

  private UserService userService;

  private UserResource() {}

  UserResource(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(consumes = "application/json")
  public UserEntity createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
    return userService.createUser(userCreateDto);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public UserEntity updateUser() {
    return null;
  }

  @DeleteMapping(path = "/{id}")
  public void disableUser() {
    //
  }

  @GetMapping
  public List<Job> getAllUserJobs() {
    return List.of();
  }
}
