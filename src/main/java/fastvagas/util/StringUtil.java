package fastvagas.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

    public static String capitalize(String text) {
        return StringUtils.capitalize(text);
    }

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
}
