package fastvagas.config;

import fastvagas.handler.CustomAuthenticationSuccessHandler;
import fastvagas.handler.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        final String usernameQuery = "select email, password, enabled "
            + "from person where email = ?";

        final String authorityQuery = "select email, authority "
            + "from authorities where email = ?";

        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery(usernameQuery)
            .authoritiesByUsernameQuery(authorityQuery);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                    "/",
                    "/js/**",
                    "/css/**",
                    "/img/**",
                    "/dist/**",
                    "/favicon/**",
                    "/fonts/**",
                    "/guest/**").permitAll()
            .antMatchers("/app/**").hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(successHandler)
            .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
