package fastvagas.handler;

import fastvagas.entity.Person;
import fastvagas.repository.PersonRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        Optional<Person> personOpt = personRepository.findByEmail(authentication.getName());
        personOpt.ifPresent(person -> {
            person.setLastLogin(LocalDateTime.now());
            personRepository.save(person);
        });

        response.sendRedirect("/home");
    }
}
