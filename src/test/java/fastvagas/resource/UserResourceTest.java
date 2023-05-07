package fastvagas.resource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fastvagas.exception.UserExistsException;
import fastvagas.repository.UserRepository;
import fastvagas.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserResource.class)
class UserResourceTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private UserRepository userRepository;

  @MockBean private UserService userService;

  private String getUserCreateStringDto() {
    return "{\"firstName\":\"First\"" +
    "\"lastName\":\"Last\"" +
    "\"email\":\"email@domain.com\"" +
    "\"city\":\"Joinville\"" +
    "\"state\":\"SC\"" +
    "\"terms\":\"Dev,Fullstack,Backend,Frontend\"}";
  }

  @Test
  @DisplayName("createUserDuplicatedEmailTest")
  void createUserDuplicatedEmailTest() throws Exception {
    when(userService.createUser(any())).thenThrow(new UserExistsException("email@domain.com"));

    mockMvc
        .perform(
            post("/users")
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(getUserCreateStringDto()))
        .andExpect(status().isBadRequest())
        .andReturn();
  }
}
