package fastvagas.exception;

import fastvagas.util.StringUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends GeneralException {

  public EntityNotFoundException(Class<?> classe, Object... searchParamsMap) {
    super(
        EntityNotFoundException.generateMessage(
            classe.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }

  private static String generateMessage(String entity, Map<String, String> searchParams) {
    return StringUtil.capitalize(entity) + " was not found for parameters " + searchParams;
  }

  private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
    if (entries.length % 2 == 1) {
      throw new IllegalArgumentException("Invalid entries");
    }

    return IntStream.range(0, entries.length)
        .map(i -> i * 2)
        .collect(
            HashMap::new,
            (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
            Map::putAll);
  }
}
