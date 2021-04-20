package fastvagas.rest;

import fastvagas.dal.entity.User;
import fastvagas.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/user")
class UserRest {

    @Resource
    private AuthService authService;

    @GetMapping(value = "/get-logged-user")
    public User getLoggedUser() {
        User user = authService.getUser();
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    /*
    @GetMapping(value = "/find-by-id/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/find-by-email/{email}")
    public User findByEmail(@PathVariable("email") final String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/find-all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/find-all-with-disabled")
    public List<User> findAllWithDisabled() {
        return userService.findAll(true);
    }

    @PostMapping(value = "/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(value = "/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/disable-by-id")
    public User disableById(@RequestBody User user) {
        return userService.disableById(user.getUser_id());
    }
    */
}
