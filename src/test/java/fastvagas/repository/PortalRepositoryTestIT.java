package fastvagas.repository;

import fastvagas.entity.Portal;
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
class PortalRepositoryTestIT {

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
    Assertions.assertEquals("Test", portalDb.getName());
    Assertions.assertEquals("https://jobs-search.com/jobs", portalDb.getSearchUrl());
    Assertions.assertEquals("Joinville", portalDb.getCity());
    Assertions.assertEquals("SC", portalDb.getState());
    Assertions.assertTrue(portalDb.isEnabled());
  }

  @Test
  @DisplayName("updateTest")
  @Order(2)
  void updateTest() {
    Portal portal = createPortal();
    Portal portalDb = portalRepository.save(portal);

    Assertions.assertNotNull(portalDb);
    Assertions.assertEquals("Test", portalDb.getName());
    Assertions.assertEquals("https://jobs-search.com/jobs", portalDb.getSearchUrl());
    Assertions.assertEquals("Joinville", portalDb.getCity());
    Assertions.assertEquals("SC", portalDb.getState());
    Assertions.assertTrue(portalDb.isEnabled());

    portalDb.setEnabled(Boolean.FALSE);
    Portal portalDbSaved = portalRepository.save(portalDb);

    Assertions.assertNotNull(portalDbSaved);
    Assertions.assertEquals(portalDb.getId(), portalDbSaved.getId());
    Assertions.assertFalse(portalDb.isEnabled());
  }

  @Test
  @DisplayName("deleteTest")
  @Order(3)
  void deleteTest() {
    Portal portal = createPortal();
    Portal portalDb = portalRepository.save(portal);

    Assertions.assertNotNull(portalDb);
    Assertions.assertEquals("Test", portalDb.getName());
    Assertions.assertEquals("https://jobs-search.com/jobs", portalDb.getSearchUrl());
    Assertions.assertEquals("Joinville", portalDb.getCity());
    Assertions.assertEquals("SC", portalDb.getState());
    Assertions.assertTrue(portalDb.isEnabled());

    portalRepository.deleteById(portalDb.getId());

    Optional<Portal> portalDeleted = portalRepository.findById(portalDb.getId());

    Assertions.assertTrue(portalDeleted.isEmpty());
  }

  @Test
  @DisplayName("findAllByEnabledTest")
  @Order(4)
  @Sql(scripts = {"classpath:sql/PortalRepositoryTest.sql"})
  void findAllByEnabledTest() {
    List<Portal> portalsDisabled = portalRepository.findAllByEnabled(Boolean.FALSE);

    Assertions.assertFalse(portalsDisabled.isEmpty());
    Assertions.assertEquals(1, portalsDisabled.size());

    List<Portal> portalsEnabled = portalRepository.findAllByEnabled(Boolean.TRUE);

    Assertions.assertFalse(portalsEnabled.isEmpty());
    Assertions.assertEquals(3, portalsEnabled.size());
  }
}
