package fastvagas.config;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class DateConfig {
    private static final String format = "dd/MM/yyyy HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.simpleDateFormat(format);
            builder.serializers(new DateSerializer(false, new SimpleDateFormat(format)));
        };
    }
}
