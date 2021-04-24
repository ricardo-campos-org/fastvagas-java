package fastvagas.rest;

import fastvagas.json.UserAccountJson;
import fastvagas.service.AuthService;
import fastvagas.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/base")
public class BaseRest {

    @Autowired
    AuthService authService;

    @Autowired
    BaseService baseService;

    @GetMapping(value = "/current-user")
    public UserAccountJson getLoggedUser() {
        return baseService.getCurrentUser(authService.getCurrentUser());
    }
}
