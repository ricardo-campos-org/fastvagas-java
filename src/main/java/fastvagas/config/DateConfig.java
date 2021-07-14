package fastvagas.config;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {
    private static final String format = "dd/MM/yyyy HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.simpleDateFormat(format);
            builder.serializers(new DateSerializer(false, new SimpleDateFormat(format)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(format)));
        };
    }
}
