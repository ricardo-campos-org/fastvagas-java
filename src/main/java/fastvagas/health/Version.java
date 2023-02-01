package fastvagas.health;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/** This class holds the build version of this service. */
@Component
public class Version implements HealthIndicator {

  @Override
  public Health health() {
    Map<String, String> map = new HashMap<>();
    map.put("build", "1.0");
    return new Health.Builder().up().withDetails(map).build();
  }
}
