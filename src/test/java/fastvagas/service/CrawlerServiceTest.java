package fastvagas.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fastvagas.entity.Portal;
import fastvagas.repository.JobRepository;
import fastvagas.repository.PortalRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CrawlerServiceTest {

  @Mock PortalRepository portalRepository;

  @Mock JobRepository jobRepository;

  @Mock MailService mailService;

  private CrawlerService crawlerService;

  @BeforeEach
  void setupTests() {
    crawlerService = new CrawlerService(portalRepository, jobRepository, mailService);
  }

  private Portal createPortal(boolean enabled) {
    return Portal.builder()
        .id(1L)
        .name("Testing")
        .searchUrl("http://joinville.com")
        .city("Joinville")
        .state("SC")
        .enabled(enabled)
        .build();
  }

  @Test
  @DisplayName("start_emptyPortalTest")
  void start_emptyPortalTest() {
    when(portalRepository.findAllByEnabled(any())).thenReturn(List.of());

    crawlerService.start();

    verify(jobRepository, times(0)).saveAll(any());
  }

  @Test
  @DisplayName("start_disabledPortalTest")
  void start_disabledPortalTest() {
    when(portalRepository.findAllByEnabled(any())).thenReturn(List.of());

    crawlerService.start();

    verify(jobRepository, times(0)).saveAll(any());
  }

  @Test
  @DisplayName("start_nonEmptyPortalTest")
  void start_nonEmptyPortalTest() {
    when(portalRepository.findAllByEnabled(any())).thenReturn(List.of(createPortal(true)));

    crawlerService.start();

    verify(jobRepository, times(1)).saveAll(any());
  }

  @Test
  @DisplayName("start_nonJobsPortalTest")
  void start_nonJobsPortalTest() {
    Portal portal = createPortal(true);
    portal.setName("TestingTwo");
    when(portalRepository.findAllByEnabled(any())).thenReturn(List.of(portal));

    crawlerService.start();

    verify(jobRepository, times(0)).saveAll(any());
  }
}
