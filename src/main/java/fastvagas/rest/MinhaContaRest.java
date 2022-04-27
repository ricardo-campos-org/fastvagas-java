package fastvagas.rest;

import fastvagas.data.entity.Person;
import fastvagas.json.UserAccountJson;
import fastvagas.service.AuthService;
import fastvagas.service.MinhaContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/minha-conta")
public class MinhaContaRest {

    @Autowired
    AuthService authService;

    @Autowired
    MinhaContaService minhaContaService;

    @PostMapping(value = "/update-user-data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserData(@RequestParam UserAccountJson userAccount) {
        Person person = authService.getCurrentUser();
        minhaContaService.updateUserData(person, userAccount);
    }
}
