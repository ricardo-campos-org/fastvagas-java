package fastvagas.util;

import fastvagas.entity.Job;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PortalJobUtilTest {

  @Test
  @DisplayName("listToMapByUrlTest")
  void listToMapByUrlTest() {
    Job jobOne = new Job();
    jobOne.setId(1L);
    jobOne.setJobTitle("Java Developer");
    jobOne.setCompanyName("Encora");
    jobOne.setJobType("Full-time");
    jobOne.setJobDescription("Code for fun, but all the time");
    jobOne.setPublishedAt("Monday, 7th April, 2023");
    jobOne.setJobUrl("http://test-job.com/one");
    jobOne.setPortalId(1L);
    jobOne.setCreatedAt(LocalDateTime.now());

    Job jobTwo = new Job();
    jobTwo.setId(2L);
    jobTwo.setJobTitle("Java Testes");
    jobTwo.setCompanyName("Encora");
    jobTwo.setJobType("Part-time");
    jobTwo.setJobDescription("Test code");
    jobTwo.setPublishedAt("Monday, 7th April, 2023");
    jobTwo.setJobUrl("http://test-job.com/two");
    jobTwo.setPortalId(1L);
    jobTwo.setCreatedAt(LocalDateTime.now());

    List<Job> jobList = List.of(jobOne, jobTwo);

    Map<String, Job> jobMap = PortalJobUtil.listToMapByUrl(jobList);

    Assertions.assertFalse(jobMap.isEmpty());
    Assertions.assertEquals(2, jobMap.size());
    Assertions.assertTrue(jobMap.containsKey("http://test-job.com/one"));
    Assertions.assertTrue(jobMap.containsKey("http://test-job.com/two"));
  }
}
