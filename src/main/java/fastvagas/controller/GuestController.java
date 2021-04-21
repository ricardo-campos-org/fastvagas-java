package fastvagas.controller;

import fastvagas.dal.entity.City;
import fastvagas.dal.entity.Contact;
import fastvagas.dal.entity.User;
import fastvagas.dal.service.CityService;
import fastvagas.dal.service.ContactService;
import fastvagas.dal.service.UserService;
import fastvagas.exception.InvalidEmailException;
import fastvagas.service.CrowlerService;
import fastvagas.service.MailService;
import fastvagas.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private MailService mailService;

    @Autowired
    private CrowlerService crowlerService;

    // New account modal form URLs
    @GetMapping(value = "/find-all-cities-by-state/{uf}")
    public List<City> findAllCitiesByUf(@PathVariable("uf") String uf) {
        return cityService.findAllByStateSigla(uf);
    }

    @GetMapping(value = "/validate-state-city/{sigla_uf}/{city_id}")
    public Boolean validateStateCity(@PathVariable("sigla_uf") String sigla_uf, @PathVariable("city_id") Long city_id) {
        return cityService.validateStateCity(sigla_uf, city_id);
    }

    @GetMapping(value = "/email-available/{email}")
    public Boolean isEmailAvailable(@PathVariable("email") String email) {
        return userService.findByEmail(email) == null;
    }

    @PostMapping(value = "/create-user")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    // Contact form URLs
    @PostMapping(value = "/contact", produces = "application/json")
    public Contact send(@RequestBody Contact contact) {
        try {
            MailUtil.validateEmailAddress(contact.getEmail());
        } catch (AddressException adress) {
            throw new InvalidEmailException(
                "E-mail inválido!",
                adress,
                adress.getLocalizedMessage()
            );
        }

        mailService.send(contact);
        contactService.create(contact);

        return contact;
    }

    // Crowler tests
    @GetMapping(value = "/crowler", produces = "application/json")
    public Map<String, String> crowlerTests() {
        crowlerService.start();
        Map<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
}