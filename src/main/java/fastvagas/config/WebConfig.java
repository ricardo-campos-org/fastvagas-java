package fastvagas.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/dist/**",
                "/js/**",
                "/img/**",
                "/favicon/**"
        ).addResourceLocations(
                "classpath:/static/dist/css",
                "classpath:/static/dist/js",
                "classpath:/static/dist/webfonts"
        );
    }
}
