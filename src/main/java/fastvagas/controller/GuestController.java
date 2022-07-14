package fastvagas.controller;

import fastvagas.entity.City;
import fastvagas.entity.Contact;
import fastvagas.entity.CrawlerLog;
import fastvagas.entity.Person;
import fastvagas.entity.PortalJob;
import fastvagas.entity.State;
import fastvagas.repository.ContactRepository;
import fastvagas.repository.PersonRepository;
import fastvagas.repository.PortalJobRepository;
import fastvagas.repository.StateRepository;
import fastvagas.exception.InvalidEmailException;
import fastvagas.repository.CityRepository;
import fastvagas.repository.CrawlerLogRepository;
import fastvagas.json.PortalJobResponse;
import fastvagas.service.CrawlerService;
import fastvagas.service.JobService;
import fastvagas.service.MailService;
import fastvagas.util.DateUtil;
import fastvagas.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private final MailService mailService;
    private final CrawlerService crawlerService;
    private final CrawlerLogRepository crawlerLogRepository;
    private final PortalJobRepository portalJobRepository;
    private final JobService jobService;

    @Autowired
    public GuestController(CityRepository cityRepository, PersonRepository personRepository,
                           ContactRepository contactRepository, MailService mailService, CrawlerService crawlerService,
                           CrawlerLogRepository crawlerLogRepository, PortalJobRepository portalJobRepository,
                           JobService jobService, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
        this.mailService = mailService;
        this.crawlerService = crawlerService;
        this.crawlerLogRepository = crawlerLogRepository;
        this.portalJobRepository = portalJobRepository;
        this.jobService = jobService;
        this.stateRepository = stateRepository;
    }

    // New account modal form URLs
    @GetMapping(value = "/find-all-cities-by-state/{uf}")
    public List<City> findAllCitiesByUf(@PathVariable("uf") String uf) {
        Optional<State> state = stateRepository.findByAcronym(uf);
        if (state.isEmpty()) {
            return null; // 204
        }
        return cityRepository.findAllByStateId(state.get().getId());
    }

    @GetMapping(value = "/validate-state-city/{sigla_uf}/{city_id}")
    public Boolean validateStateCity(@PathVariable("sigla_uf") String sigla_uf, @PathVariable("city_id") Long city_id) {
        return Boolean.FALSE; // cityRepository.validateStateCity(sigla_uf, city_id);
    }

    @GetMapping(value = "/email-available/{email}")
    public Boolean isEmailAvailable(@PathVariable("email") String email) {
        return personRepository.findByEmail(email).isEmpty();
    }

    @PostMapping(value = "/create-user")
    public Person createUser(@RequestBody Person person) {
        return personRepository.save(person);
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
        contactRepository.save(contact);

        return contact;
    }

    // Crawler tests API
    @PostMapping(value = "/do-crawler", produces = "application/json")
    public ResponseEntity<?> crawlerTests() {
        crawlerService.start();
        LocalDateTime ultimoMes = DateUtil.getCurrentLocalDateTime().minusDays(31L);
        jobService.processUserJobs(ultimoMes);
        return ResponseEntity.ok().body("Done");
    }

    @GetMapping(value = "/get-logs", produces = "application/json")
    public List<CrawlerLog> getLogs() {
        LocalDateTime ontem = DateUtil.getCurrentLocalDateTime().minusDays(1L);

        return crawlerLogRepository.findAllByGreaterDateTime(ontem);
    }

    @GetMapping(value = "/get-jobs", produces = "application/json")
    public List<PortalJobResponse> getJobs() {
        LocalDateTime semanaAtual = DateUtil.getCurrentLocalDateTime().minusDays(7L);

        List<PortalJob> portalJobList = portalJobRepository.findAllByCreatedAtStartintAt(semanaAtual);
        List<PortalJobResponse> respList = new ArrayList<>(portalJobList.size());
        portalJobList.forEach(p -> respList.add(PortalJobResponse.fromPortalJob(p)));

        respList.sort(Comparator.comparing(PortalJobResponse::getName));

        return respList;
    }

    @PostMapping(value = "/reprocess-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reprocessUser(@RequestBody Person person) {
        try {
            LocalDateTime ultimoMes = DateUtil.getCurrentLocalDateTime().minusDays(31L);
            Optional<Person> personDb = personRepository.findById(person.getId());
            if (personDb.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            jobService.processUserJobs(personDb.get(), ultimoMes);
            return ResponseEntity.ok().body("Done");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get-jobs-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PortalJobResponse> getJobsByUser(@RequestParam("user_id") Long user_id) {
        List<PortalJob> portalJobList = jobService.findUserJobsByTermsNotSeen(user_id);
        List<PortalJobResponse> respList = new ArrayList<>(portalJobList.size());

        portalJobList.forEach(p -> respList.add(PortalJobResponse.fromPortalJob(p)));
        respList.sort(Comparator.comparing(PortalJobResponse::getName));

        return respList;
    }
}
