package fastvagas.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilTest {

  @Test
  @DisplayName("parseJobNameTest")
  void parseJobNameTest() {
    Assertions.assertEquals("Java Developer", StringUtil.parseJobName("java developer"));
    Assertions.assertEquals("Cozinheiro", StringUtil.parseJobName("cozinheiro"));
    Assertions.assertEquals("TI Manager", StringUtil.parseJobName("ti manager"));
  }

  @Test
  @DisplayName("capitalizeTest")
  void capitalizeTest() {
    Assertions.assertEquals("TI", StringUtil.parseJobName("ti"));
    Assertions.assertEquals("Gerente", StringUtil.parseJobName("GERENTE"));
    Assertions.assertEquals("de", StringUtil.parseJobName("de"));
  }

  @Test
  @DisplayName("replaceToPlainText")
  void replaceToPlainText() {
    Assertions.assertEquals("Maracuja", StringUtil.replaceToPlainText("Maracujá"));
    Assertions.assertEquals("Ate", StringUtil.replaceToPlainText("Até"));
    Assertions.assertEquals("Sapucai", StringUtil.replaceToPlainText("Sapucaí"));
    Assertions.assertEquals("Po", StringUtil.replaceToPlainText("Pó"));
    Assertions.assertEquals("Itu", StringUtil.replaceToPlainText("Itú"));
    Assertions.assertEquals("Itapua", StringUtil.replaceToPlainText("Itapuã"));
    Assertions.assertEquals("Caua", StringUtil.replaceToPlainText("Cauâ"));
    Assertions.assertEquals("Caue", StringUtil.replaceToPlainText("Cauê"));
    Assertions.assertEquals("So", StringUtil.replaceToPlainText("Sô"));
    Assertions.assertEquals("Saguacu", StringUtil.replaceToPlainText("Saguaçu"));
  }

  @Test
  @DisplayName("fixMaxLengthTest")
  void fixMaxLengthTest() {
    Assertions.assertEquals("Company", StringUtil.fixMaxLength("Company LLC", 7));
    Assertions.assertEquals("Ricardo Campos", StringUtil.fixMaxLength("Ricardo Campos", 30));
    Assertions.assertEquals("", StringUtil.fixMaxLength("", 7));
  }
}
