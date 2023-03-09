package fastvagas.repository;

import fastvagas.entity.Job;
import java.time.LocalDateTime;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class JobRepositoryTest {

  @Autowired private JobRepository jobRepository;

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
}
