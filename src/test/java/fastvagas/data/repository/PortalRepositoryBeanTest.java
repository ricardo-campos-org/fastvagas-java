package fastvagas.data.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import fastvagas.data.entity.Portal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PortalRepositoryBeanTest {

    @Mock
    private PortalRepositoryBean portalRepositoryBean;

    @Test
    public void findAllTest() {
        List<Portal> portals = new ArrayList<>();

        when(portalRepositoryBean.findAll()).thenReturn(portals);

        assertTrue(portals.isEmpty());
    }
}