package fastvagas.util;

import java.util.logging.Logger;

public class ObjectUtil {

    private final static Logger logger = Logger.getLogger(ObjectUtil.class.getName());

    public static boolean hasValue(Object obj) {
        if (obj == null) {
            return false;
        }

        try {
            if (obj instanceof Long) {
                Long val = (Long) obj;
                return val > 0;
            }
            if (obj instanceof Integer) {
                Integer val = (Integer) obj;
                return val > 0;
            }
            if (obj instanceof String) {
                String val = String.valueOf(obj);
                return !val.trim().isEmpty();
            }
            if (obj instanceof Character) {
                Character val = (Character) obj;
                return !val.equals(' ');
            }

            logger.info("Classe não tratada: " + obj.getClass());

            throw new RuntimeException("Classe não tratada: " + obj.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
