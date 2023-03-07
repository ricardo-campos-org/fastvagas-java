package fastvagas.repository;

import fastvagas.entity.Job;
import fastvagas.entity.Portal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class JobRepositoryTestIT {

  @Autowired private JobRepository jobRepository;

  @Autowired private PortalRepository portalRepository;

  private Job createJob() {
    Job job = new Job();
    job.setId(1L);
    job.setJobTitle("Software Developer");
    job.setCompanyName("Encora");
    job.setJobType("Full time");
    job.setJobDescription("Able to write and test code");
    job.setPublishedAt("Fri 03, Feb, 2023 at 4:10 pm");
    job.setJobUrl("http://encora.com/careers/software_developer");
    job.setPortalId(1L);
    job.setCreatedAt(LocalDateTime.now());
    return job;
  }

  @Test
  @DisplayName("createTest")
  @Order(1)
  void createTest() {
    Job job = createJob();
    Job jobDb = jobRepository.save(job);

    Assertions.assertNotNull(jobDb);
    Assertions.assertEquals(1L, jobDb.getId());
    Assertions.assertEquals("Software Developer", jobDb.getJobTitle());
    Assertions.assertEquals("Encora", jobDb.getCompanyName());
    Assertions.assertEquals("Full time", jobDb.getJobType());
    Assertions.assertEquals("Able to write and test code", jobDb.getJobDescription());
  }

  @Test
  @DisplayName("updateTest")
  @Order(2)
  void updateTest() {
    Job job = createJob();
    Job jobDb = jobRepository.save(job);

    Assertions.assertNotNull(jobDb);
    Assertions.assertEquals(2L, jobDb.getId());
    Assertions.assertEquals("Software Developer", jobDb.getJobTitle());
    Assertions.assertEquals("Encora", jobDb.getCompanyName());
    Assertions.assertEquals("Full time", jobDb.getJobType());
    Assertions.assertEquals("Able to write and test code", jobDb.getJobDescription());

    jobDb.setJobType("Part-time");
    Job jobDbSaved = jobRepository.save(jobDb);

    Assertions.assertNotNull(jobDbSaved);
    Assertions.assertEquals(2L, jobDbSaved.getId());
    Assertions.assertEquals("Part-time", jobDbSaved.getJobType());
  }

  @Test
  @DisplayName("deleteTest")
  @Order(3)
  void deleteTest() {
    Job job = createJob();
    Job jobDb = jobRepository.save(job);

    Assertions.assertNotNull(jobDb);
    Assertions.assertEquals(3L, jobDb.getId());
    Assertions.assertEquals("Software Developer", jobDb.getJobTitle());
    Assertions.assertEquals("Encora", jobDb.getCompanyName());
    Assertions.assertEquals("Full time", jobDb.getJobType());
    Assertions.assertEquals("Able to write and test code", jobDb.getJobDescription());

    jobRepository.deleteById(jobDb.getId());

    Optional<Job> portalDeleted = jobRepository.findById(jobDb.getId());

    Assertions.assertTrue(portalDeleted.isEmpty());
  }

  @Test
  @DisplayName("findAllByPortalId")
  @Order(4)
  @Sql(scripts = {"classpath:sql/JobRepositoryTest.sql"})
  void findAllByPortalId() {
    Optional<Portal> portal = portalRepository.findByName("Portal One");
    Assertions.assertTrue(portal.isPresent());

    List<Job> jobs = jobRepository.findAllByPortalId(portal.get().getId());
    Assertions.assertFalse(jobs.isEmpty());
    Assertions.assertEquals(2, jobs.size());

    List<Job> jobsEmpty = jobRepository.findAllByPortalId(2L);
    Assertions.assertTrue(jobsEmpty.isEmpty());
  }

  @Test
  @DisplayName("findAllByCreatedStartingAt")
  @Order(5)
  @Sql(scripts = {"classpath:sql/JobRepositoryTest.sql"})
  void findAllByCreatedStartingAt() {
    List<Job> jobs = jobRepository.findAllByCreatedStartingAt(LocalDateTime.parse("2023-02-01T10:05:02"));
    Assertions.assertFalse(jobs.isEmpty());
    Assertions.assertEquals(1, jobs.size());

    Job job = jobs.get(0);
    Assertions.assertEquals("Java Back-end", job.getJobTitle());
    Assertions.assertEquals("Encora", job.getCompanyName());
    Assertions.assertEquals("Full-time", job.getJobType());
    Assertions.assertEquals("Write code", job.getJobDescription());

    List<Job> jobsMore = jobRepository.findAllByCreatedStartingAt(LocalDateTime.parse("2023-01-01T10:05:02"));
    Assertions.assertFalse(jobsMore.isEmpty());
    Assertions.assertEquals(2, jobsMore.size());
  }
}
