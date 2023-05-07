package fastvagas.util;

import jakarta.validation.constraints.NotNull;
import java.util.Map;
import org.springframework.util.StringUtils;

/** This class contains useful method to adjust text from the jobs. */
public class TextUtil {

  private TextUtil() {}

  private static final String IGNORE_LIST = "de, e, em";

  private static final Map<String, String> replacer =
      Map.of(
          "cnc", "CNC",
          "i", "I",
          "ii", "II",
          "rh", "RH",
          "ti", "TI",
          "web", "WEB");

  /**
   * Check if a given word should be ignored, that is, not capitalized or changed.
   *
   * @param word The word to be checked
   * @return true if yes, false otherwise
   */
  public static boolean isIgnore(String word) {
    return IGNORE_LIST.contains(word);
  }

  /**
   * Replace a given word by an equivalent one. E.g.: rh by RH.
   *
   * @param word The word to be replaced
   * @return The new word
   */
  public static String replace(@NotNull String word) {
    return replacer.getOrDefault(word.toLowerCase(), StringUtils.capitalize(word));
  }
}
