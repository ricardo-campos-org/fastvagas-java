package fastvagas.repository;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import fastvagas.entity.Portal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PortalRepositoryTest {

  @MockBean PortalRepository portalRepository;

  @Test
  void saveTest() {
    Portal portal = new Portal();
    portal.setId(1L);

    portalRepository.save(portal);

    when(portalRepository.findById(eq(portal.getId()))).thenReturn(Optional.of(portal));

    Optional<Portal> portalOptional = portalRepository.findById(1L);

    Assertions.assertTrue(portalOptional.isPresent());
  }

  @Test
  public void findAllTest() {
    List<Portal> portals = new ArrayList<>();

    when(portalRepository.findAll()).thenReturn(portals);

    Assertions.assertTrue(portals.isEmpty());
  }
}
