package fastvagas.data.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import fastvagas.entity.Portal;
import fastvagas.repository.PortalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PortalRepositoryBeanTest {

    @Mock
    private PortalRepository portalRepository;

    @Test
    public void findAllTest() {
        List<Portal> portals = new ArrayList<>();

        when(portalRepository.findAll()).thenReturn(portals);

        assertTrue(portals.isEmpty());
    }
}