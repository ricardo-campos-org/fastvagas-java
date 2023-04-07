package fastvagas.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilTest {

  void parseJobNameTest() {
    //
  }

  void capitalizeTest() {
    //
  }

  @Test
  @DisplayName("replaceToPlainText")
  void replaceToPlainText() {
    //
  }

  @Test
  @DisplayName("fixMaxLengthTest")
  void fixMaxLengthTest() {
    Assertions.assertEquals("Company", StringUtil.fixMaxLength("Company LLC", 7));
    Assertions.assertEquals("Ricardo Campos", StringUtil.fixMaxLength("Ricardo Campos", 30));
  }
}
