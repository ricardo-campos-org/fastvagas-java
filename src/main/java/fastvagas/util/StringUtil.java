package fastvagas.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** This class contains useful methods to handle strings and texts. */
public class StringUtil {

  private StringUtil() {}

  /**
   * Normalize a given job title making it capitalized.
   *
   * @param text The text to be normalized
   * @return A string with the new parsed text
   */
  public static String parseJobName(String text) {
    if (Objects.isNull(text)) {
      return text;
    }
    List<String> words = List.of(text.split(" "));
    StringBuilder sb = new StringBuilder();
    for (String word : words) {
      if (!sb.toString().isEmpty()) {
        sb.append(" ");
      }

      sb.append(capitalize(word));
    }

    return sb.toString();
  }

  /**
   * Capitalize a given text title making only the first letter uppercase.
   *
   * @param text The text to be capitalized
   * @return A string with the new capitalized text
   */
  public static String capitalize(String text) {
    if (TextUtil.isIgnore(text)) {
      return text;
    }

    text = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();

    return TextUtil.replace(text);
  }

  /**
   * Replaces special characters with plain characters.
   *
   * @param text The original text to be replaced
   * @return The new text with only plain text
   */
  public static String replaceToPlainText(String text) {
    Map<Character, Character> charMap = new HashMap<>();
    charMap.put('á', 'a');
    charMap.put('é', 'e');
    charMap.put('í', 'i');
    charMap.put('ó', 'o');
    charMap.put('ú', 'u');
    charMap.put('ç', 'c');
    charMap.put('ã', 'a');
    charMap.put('â', 'a');
    charMap.put('ê', 'e');
    charMap.put('ô', 'o');
    charMap.put('Á', 'A');
    charMap.put('É', 'E');
    charMap.put('Í', 'I');
    charMap.put('Ó', 'O');
    charMap.put('Ú', 'U');
    charMap.put('Ç', 'C');
    charMap.put('Ã', 'A');
    charMap.put('Â', 'A');
    charMap.put('Ô', 'O');

    StringBuilder sb = new StringBuilder();
    for (Character charFor : text.toCharArray()) {
      Character newchar = charMap.get(charFor);
      if (newchar == null) {
        sb.append(charFor);
      } else {
        sb.append(newchar);
      }
    }

    return sb.toString();
  }

  /**
   * Cut a given text to a given maximum length.
   *
   * @param text The text to be cut
   * @param max The maximum length
   * @return the new text
   */
  public static String fixMaxLength(String text, int max) {
    if (text.isEmpty() || text.isBlank()) {
      return text;
    }

    if (text.length() > max) {
      return text.substring(0, max);
    }

    return text;
  }
}
