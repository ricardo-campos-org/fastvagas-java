package fastvagas.controller;

import fastvagas.entity.City;
import fastvagas.entity.CrawlerLog;
import fastvagas.entity.Person;
import fastvagas.entity.PortalJob;
import fastvagas.entity.State;
import fastvagas.json.PortalJobResponse;
import fastvagas.repository.CityRepository;
import fastvagas.repository.CrawlerLogRepository;
import fastvagas.repository.PersonRepository;
import fastvagas.repository.PortalJobRepository;
import fastvagas.repository.StateRepository;
import fastvagas.service.CrawlerService;
import fastvagas.service.JobService;
import fastvagas.util.DateUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** This class contains all endpoints to guest users. */
@Setter
@NoArgsConstructor
@RestController
@RequestMapping("/guest")
public class GuestController {

  private CityRepository cityRepository;
  private StateRepository stateRepository;
  private PersonRepository personRepository;
  private CrawlerService crawlerService;
  private CrawlerLogRepository crawlerLogRepository;
  private PortalJobRepository portalJobRepository;
  private JobService jobService;

  /**
   * Creates a GuestController instance.
   *
   * @param cityRepository cityRepository instance
   * @param personRepository personRepository instance
   * @param crawlerService crawlerService instance
   * @param crawlerLogRepository crawlerLogRepository instance
   * @param portalJobRepository portalJobRepository intance
   * @param jobService jobService instance
   * @param stateRepository stateRepository instance
   */
  @Autowired
  public GuestController(
      CityRepository cityRepository,
      PersonRepository personRepository,
      CrawlerService crawlerService,
      CrawlerLogRepository crawlerLogRepository,
      PortalJobRepository portalJobRepository,
      JobService jobService,
      StateRepository stateRepository) {
    this.cityRepository = cityRepository;
    this.personRepository = personRepository;
    this.crawlerService = crawlerService;
    this.crawlerLogRepository = crawlerLogRepository;
    this.portalJobRepository = portalJobRepository;
    this.jobService = jobService;
    this.stateRepository = stateRepository;
  }

  /**
   * Find all cities by a given state.
   *
   * @param uf state to search
   * @return a list of City or an empty list
   */
  // New account modal form URLs
  @GetMapping(value = "/find-all-cities-by-state/{uf}")
  public List<City> findAllCitiesByUf(@PathVariable("uf") String uf) {
    Optional<State> state = stateRepository.findByAcronym(uf);
    return state.map(value -> cityRepository.findAllByStateId(value.getId())).orElse(null);
  }

  /**
   * Check if a given city is from a given state.
   *
   * @param siglaUf initials of the state
   * @param cityId if of the city
   * @return true if yes, false otherwise
   */
  @GetMapping(value = "/validate-state-city/{siglaUf}/{cityId}")
  public Boolean validateStateCity(
      @PathVariable("sigla_uf") String siglaUf, @PathVariable("city_id") Long cityId) {
    return Boolean.FALSE; // cityRepository.validateStateCity(siglaUf, cityId);
  }

  /**
   * Check if a given email is available.
   *
   * @param email String email to be checked
   * @return true if is available, false otherwise
   */
  @GetMapping(value = "/email-available/{email}")
  public Boolean isEmailAvailable(@PathVariable("email") String email) {
    return personRepository.findByEmail(email).isEmpty();
  }

  /**
   * Creates a Person.
   *
   * @param person the person to be created
   * @return the person instance created
   */
  @PostMapping(value = "/create-user")
  public Person createUser(@RequestBody Person person) {
    return personRepository.save(person);
  }

  /**
   * Starts the crawler task.
   *
   * @return an Entity response containing a string Done if success
   */
  @PostMapping(value = "/do-crawler", produces = "application/json")
  public ResponseEntity<?> crawlerTests() {
    crawlerService.start();
    LocalDateTime ultimoMes = LocalDateTime.now().minusDays(31L);
    jobService.processUserJobs(ultimoMes);
    return ResponseEntity.ok().body("Done");
  }

  /**
   * Retrieve all logs.
   *
   * @return a list of CrawlerLog containing the logs
   */
  @GetMapping(value = "/get-logs", produces = "application/json")
  public List<CrawlerLog> getLogs() {
    LocalDateTime ontem = LocalDateTime.now().minusDays(1L);

    return crawlerLogRepository.findAllByGreaterDateTime(ontem);
  }

  /**
   * Retrieve all jobs from the last 7 days.
   *
   * @return a list of PortalJobResponse with the jobs
   */
  @GetMapping(value = "/get-jobs", produces = "application/json")
  public List<PortalJobResponse> getJobs() {
    LocalDateTime semanaAtual = LocalDateTime.now().minusDays(7L);

    List<PortalJob> portalJobList = portalJobRepository.findAllByCreatedAtStartingAt(semanaAtual);
    List<PortalJobResponse> respList = new ArrayList<>(portalJobList.size());
    portalJobList.forEach(p -> respList.add(PortalJobResponse.fromPortalJob(p)));

    respList.sort(Comparator.comparing(PortalJobResponse::getName));

    return respList;
  }

  /**
   * Triggers a reprocessing of a user's jobs.
   *
   * @param person the person to be processed
   * @return a ResponseEntity with the response
   */
  @PostMapping(
      value = "/reprocess-user",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> reprocessUser(@RequestBody Person person) {
    try {
      LocalDateTime ultimoMes = LocalDateTime.now().minusDays(31L);
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

  /**
   * Retrieves all jobs from a user.
   *
   * @param userId the user id
   * @return a list of PortalJobResponse
   */
  @GetMapping(value = "/get-jobs-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<PortalJobResponse> getJobsByUser(@RequestParam("userId") Long userId) {
    List<PortalJob> portalJobList = jobService.findUserJobsByTermsNotSeen(userId);
    List<PortalJobResponse> respList = new ArrayList<>(portalJobList.size());

    portalJobList.forEach(p -> respList.add(PortalJobResponse.fromPortalJob(p)));
    respList.sort(Comparator.comparing(PortalJobResponse::getName));

    return respList;
  }
}
