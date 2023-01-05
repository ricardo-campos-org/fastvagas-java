package fastvagas.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateUtilTest {

  @Test
  @DisplayName("getMonthFromDate")
  void getMonthFromDateTest() throws Exception {
    Date jan = DateUtil.createDate(1, 1, 2023);
    Assertions.assertEquals(1, DateUtil.getMonthFromDate(jan));
  }
}
