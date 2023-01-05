package fastvagas.util;

import java.util.Map;
import org.springframework.util.StringUtils;

public final class TextUtil {

  private static final String IGNORE_LIST = "de, e, em";

  private static final Map<String, String> replacer =
      Map.of(
          "cnc", "CNC",
          "i", "I",
          "ii", "II",
          "rh", "RH",
          "ti", "TI",
          "web", "WEB");

  public static boolean isIgnore(String word) {
    return IGNORE_LIST.contains(word);
  }

  public static String replace(String word) {
    return replacer.getOrDefault(word, StringUtils.capitalize(word));
  }
}
