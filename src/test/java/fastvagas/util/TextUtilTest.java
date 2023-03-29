package fastvagas.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextUtilTest {

  @Test
  @DisplayName("isIgnoreTest")
  void isIgnoreTest() {
    Assertions.assertTrue(TextUtil.isIgnore("de"));
    Assertions.assertTrue(TextUtil.isIgnore("e"));
    Assertions.assertTrue(TextUtil.isIgnore("em"));
    Assertions.assertFalse(TextUtil.isIgnore("a"));
  }

  @Test
  @DisplayName("replaceTest")
  void replaceTest() {
    Assertions.assertEquals("CNC", TextUtil.replace("cnc"));
    Assertions.assertEquals("CNC", TextUtil.replace("Cnc"));
    Assertions.assertEquals("I", TextUtil.replace("i"));
    Assertions.assertEquals("II", TextUtil.replace("ii"));
    Assertions.assertEquals("RH", TextUtil.replace("rh"));
    Assertions.assertEquals("RH", TextUtil.replace("Rh"));
    Assertions.assertEquals("TI", TextUtil.replace("ti"));
    Assertions.assertEquals("WEB", TextUtil.replace("web"));
  }
}
