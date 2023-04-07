package fastvagas.util;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateUtilTest {

  @Test
  @DisplayName("formatLocalDateTimeTest")
  void formatLocalDateTimeTest() {
    LocalDateTime time = LocalDateTime.of(2023, 1, 6, 8, 8);
    String formatted = DateUtil.formatLocalDateTime(time);

    Assertions.assertEquals("06/01/2023 08:08:00", formatted);
  }

  @Test
  @DisplayName("formatEmptyDateTimeTest")
  void formatEmptyDateTimeTest() {
    Assertions.assertEquals("", DateUtil.formatLocalDateTime(null));
  }
}
