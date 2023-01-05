package fastvagas.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectUtil {

  public static boolean hasValue(Object... objs) {
    for (Object obj : objs) {
      if (!hasValue(obj)) {
        return false;
      }
    }
    return true;
  }

  public static boolean hasValue(Object obj) {
    if (obj == null) {
      return false;
    }

    try {
      if (obj instanceof Long) {
        Long val = (Long) obj;
        return val > 0;
      } else if (obj instanceof Integer) {
        Integer val = (Integer) obj;
        return val > 0;
      } else if (obj instanceof BigDecimal) {
        BigDecimal val = (BigDecimal) obj;
        return val.compareTo(BigDecimal.ZERO) != 0;
      } else if (obj instanceof String) {
        String val = String.valueOf(obj);
        return !val.trim().isEmpty();
      } else if (obj instanceof Character) {
        Character val = (Character) obj;
        return !val.equals(' ');
      } else if (obj.getClass().isArray()) {
        return Array.getLength(obj) > 0;
      } else if (obj instanceof List) {
        List<?> val = (List<?>) obj;
        return !val.isEmpty();
      }

      log.info("Classe não tratada: " + obj.getClass());

      throw new RuntimeException("Classe não tratada: " + obj.getClass());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static String fixMaxLength(String text, Integer max) {
    if (!hasValue(text)) {
      return text;
    }

    if (text.length() > max) {
      return text.substring(0, max);
    }

    return text;
  }
}
