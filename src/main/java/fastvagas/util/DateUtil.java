package fastvagas.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/** This class contains useful Date and Time conversions and parsers. */
public class DateUtil {

  /**
   * Format a {@link LocalDateTime} to 'dd/MM/yyyy hh:mm:ss' format.
   *
   * @param localDateTime A {@link LocalDateTime} instance
   * @return The localDateTime formatted
   */
  public static String formatLocalDateTime(LocalDateTime localDateTime) {
    if (Optional.ofNullable(localDateTime).isEmpty()) {
      return "";
    }
    String fmt = "dd/MM/yyyy hh:mm:ss";
    return DateTimeFormatter.ofPattern(fmt).format(localDateTime);
  }
}
