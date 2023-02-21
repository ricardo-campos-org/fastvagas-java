package fastvagas.repository;

import fastvagas.entity.Portal;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class PortalRepositoryTest {

  @Autowired private PortalRepository portalRepository;

  private Portal createPortal() {
    Portal portal = new Portal();
    portal.setId(1L);
    portal.setName("Test");
    portal.setSearchUrl("https://jobs-search.com/jobs");
    portal.setCity("Joinville");
    portal.setState("SC");
    portal.setEnabled(Boolean.TRUE);
    return portal;
  }

  @Test
  @DisplayName("createTest")
  @Order(1)
  void createTest() {
    Portal portal = createPortal();
    Portal portalDb = portalRepository.save(portal);

    Assertions.assertNotNull(portalDb);
    Assertions.assertEquals(1L, portalDb.getId());
    Assertions.assertEquals("Test", portalDb.getName());
    Assertions.assertEquals("https://jobs-search.com/jobs", portalDb.getSearchUrl());
    Assertions.assertEquals("Joinville", portalDb.getCity());
    Assertions.assertEquals("SC", portalDb.getState());
    Assertions.assertTrue(portalDb.getEnabled());
  }

  @Test
  @DisplayName("updateTest")
  @Order(2)
  void updateTest() {
    Portal portal = createPortal();
    Portal portalDb = portalRepository.save(portal);

    Assertions.assertNotNull(portalDb);
    Assertions.assertEquals(2L, portalDb.getId());
    Assertions.assertEquals("Test", portalDb.getName());
    Assertions.assertEquals("https://jobs-search.com/jobs", portalDb.getSearchUrl());
    Assertions.assertEquals("Joinville", portalDb.getCity());
    Assertions.assertEquals("SC", portalDb.getState());
    Assertions.assertTrue(portalDb.getEnabled());

    portalDb.setEnabled(Boolean.FALSE);
    Portal portalDbSaved = portalRepository.save(portalDb);

    Assertions.assertNotNull(portalDbSaved);
    Assertions.assertEquals(2L, portalDb.getId());
    Assertions.assertFalse(portalDb.getEnabled());
  }

  @Test
  @DisplayName("deleteTest")
  @Order(3)
  void deleteTest() {
    Portal portal = createPortal();
    Portal portalDb = portalRepository.save(portal);

    Assertions.assertNotNull(portalDb);
    Assertions.assertEquals(3L, portalDb.getId());
    Assertions.assertEquals("Test", portalDb.getName());
    Assertions.assertEquals("https://jobs-search.com/jobs", portalDb.getSearchUrl());
    Assertions.assertEquals("Joinville", portalDb.getCity());
    Assertions.assertEquals("SC", portalDb.getState());
    Assertions.assertTrue(portalDb.getEnabled());

    portalRepository.deleteById(portalDb.getId());

    Optional<Portal> portalDeleted = portalRepository.findById(portalDb.getId());

    Assertions.assertTrue(portalDeleted.isEmpty());
  }
}
