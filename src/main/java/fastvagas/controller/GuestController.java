package fastvagas.controller;

import fastvagas.data.entity.City;
import fastvagas.data.entity.Contact;
import fastvagas.data.entity.CrowlerLog;
import fastvagas.data.entity.PortalJob;
import fastvagas.data.entity.User;
import fastvagas.data.repository.CityService;
import fastvagas.data.repository.ContactService;
import fastvagas.data.repository.CrowlerLogService;
import fastvagas.data.repository.PortalJobService;
import fastvagas.data.repository.UserService;
import fastvagas.exception.InvalidEmailException;
import fastvagas.json.PortalJobResponse;
import fastvagas.service.CrowlerService;
import fastvagas.service.MailService;
import fastvagas.util.DateUtil;
import fastvagas.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final CityService cityService;
    private final UserService userService;
    private final ContactService contactService;
    private final MailService mailService;
    private final CrowlerService crowlerService;
    private final CrowlerLogService crowlerLogService;
    private final PortalJobService portalJobService;

    @Autowired
    public GuestController(CityService cityService, UserService userService, ContactService contactService,
                           MailService mailService, CrowlerService crowlerService, CrowlerLogService crowlerLogService,
                           PortalJobService portalJobService) {
        this.cityService = cityService;
        this.userService = userService;
        this.contactService = contactService;
        this.mailService = mailService;
        this.crowlerService = crowlerService;
        this.crowlerLogService = crowlerLogService;
        this.portalJobService = portalJobService;
    }

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
    @PostMapping(value = "/do-crowler", produces = "application/json")
    public List<PortalJobResponse> crowlerTests() {
        crowlerService.start();

        List<PortalJob> portalJobList = portalJobService.findAllByNotSeen();
        List<PortalJobResponse> respList = new ArrayList<>(portalJobList.size());
        portalJobList.forEach(p -> respList.add(PortalJobResponse.fromPortalJob(p)));

        respList.sort(Comparator.comparing(PortalJobResponse::getName));

        return respList;
    }

    @GetMapping(value = "/get-logs", produces = "application/json")
    public List<CrowlerLog> getLogs() {
        LocalDateTime ontem = DateUtil.getCurrentLocalDateTime().minusDays(1L);

        return crowlerLogService.findAllByGreaterDateTime(ontem);
    }

    @GetMapping(value = "/get-jobs", produces = "application/json")
    public List<PortalJobResponse> getJobs() {
        LocalDateTime ontem = DateUtil.getCurrentLocalDateTime().minusDays(1L);

        List<PortalJob> portalJobList = portalJobService.findAllByNotSeen();
        List<PortalJobResponse> respList = new ArrayList<>(portalJobList.size());
        portalJobList.forEach(p -> respList.add(PortalJobResponse.fromPortalJob(p)));

        respList.sort(Comparator.comparing(PortalJobResponse::getName));

        return respList;
    }
}
