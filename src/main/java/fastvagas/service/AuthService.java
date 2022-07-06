package fastvagas.service;

import fastvagas.data.entity.Person;
import fastvagas.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PersonRepository personRepository;

    @Autowired
    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        return personRepository.findByEmail(userDetails.getUsername()).orElse(null);
    }
}
