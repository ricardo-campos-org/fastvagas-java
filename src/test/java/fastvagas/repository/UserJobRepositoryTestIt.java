package fastvagas.repository;

import fastvagas.entity.UserJob;
import fastvagas.entity.UserJobPk;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class UserJobRepositoryTestIt {

  @Autowired private UserJobRepository userJobRepository;

  @Test
  @DisplayName("createTest")
  @Order(1)
  void createTest() {
    UserJob userJob = new UserJob();
    userJob.setId(new UserJobPk(1L, 1L));
    userJob.setEmailSent(null);
    userJob.setTerm("receptionist");

    UserJob userJobDb = userJobRepository.save(userJob);

    Assertions.assertNotNull(userJobDb);
    Assertions.assertNotNull(userJobDb.getId());
    Assertions.assertEquals(1L, userJobDb.getId().getUserId());
    Assertions.assertEquals(1L, userJobDb.getId().getJobId());
    Assertions.assertNull(userJobDb.getEmailSent());
    Assertions.assertEquals("receptionist", userJobDb.getTerm());
  }

  @Test
  @DisplayName("updateTest")
  @Order(2)
  void updateTest() {
    UserJob userJob = new UserJob();
    userJob.setId(new UserJobPk(1L, 1L));
    userJob.setEmailSent(null);
    userJob.setTerm("receptionist");

    UserJob userJobDb = userJobRepository.save(userJob);

    Assertions.assertNotNull(userJobDb);
    Assertions.assertNotNull(userJobDb.getId());
    Assertions.assertEquals(1L, userJobDb.getId().getUserId());
    Assertions.assertEquals(1L, userJobDb.getId().getJobId());
    Assertions.assertNull(userJobDb.getEmailSent());
    Assertions.assertEquals("receptionist", userJobDb.getTerm());

    LocalDateTime timeSent = LocalDateTime.of(2023, 2, 3, 3, 43);
    userJobDb.setEmailSent(timeSent);

    UserJob userJobUpdated = userJobRepository.save(userJobDb);

    Assertions.assertNotNull(userJobUpdated);
    Assertions.assertNotNull(userJobUpdated.getId());
    Assertions.assertEquals(1L, userJobUpdated.getId().getUserId());
    Assertions.assertEquals(1L, userJobUpdated.getId().getJobId());
    Assertions.assertNotNull(userJobUpdated.getEmailSent());
    Assertions.assertEquals("receptionist", userJobUpdated.getTerm());
    Assertions.assertEquals(timeSent, userJobUpdated.getEmailSent());
  }

  @Test
  @DisplayName("retrieveTestByUser")
  @Order(3)
  @Sql(scripts = {"classpath:sql/UserJobRepositoryTest_findByUser.sql"})
  void retrieveTestByUser() {
    List<UserJob> userJobList = userJobRepository.findAllByUserId(10001L);

    Assertions.assertFalse(userJobList.isEmpty());
    Assertions.assertEquals(3, userJobList.size());

    Assertions.assertEquals(10001L, userJobList.get(0).getId().getUserId());
    Assertions.assertEquals(11001L, userJobList.get(0).getId().getJobId());
    Assertions.assertNull(userJobList.get(0).getEmailSent());
    Assertions.assertEquals("tester", userJobList.get(0).getTerm());

    Assertions.assertEquals(10001L, userJobList.get(1).getId().getUserId());
    Assertions.assertEquals(11002L, userJobList.get(1).getId().getJobId());
    Assertions.assertNull(userJobList.get(1).getEmailSent());
    Assertions.assertEquals("qa", userJobList.get(1).getTerm());

    Assertions.assertEquals(10001L, userJobList.get(2).getId().getUserId());
    Assertions.assertEquals(11003L, userJobList.get(2).getId().getJobId());
    Assertions.assertNull(userJobList.get(2).getEmailSent());
    Assertions.assertEquals("sdet", userJobList.get(2).getTerm());
  }

  @Test
  @DisplayName("deleteTest")
  @Order(4)
  @Sql(scripts = {"classpath:sql/UserJobRepositoryTest_findByUser.sql"})
  void deleteTest() {
    List<UserJob> userJobList = userJobRepository.findAllByUserId(10001L);

    Assertions.assertFalse(userJobList.isEmpty());
    Assertions.assertEquals(3, userJobList.size());

    UserJob userJobOne = userJobList.get(0);
    userJobRepository.delete(userJobOne);

    userJobList = userJobRepository.findAllByUserId(10001L);

    Assertions.assertFalse(userJobList.isEmpty());
    Assertions.assertEquals(2, userJobList.size());
  }
}
