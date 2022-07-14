package fastvagas.service;

import fastvagas.entity.Person;
import fastvagas.repository.PersonRepository;
import fastvagas.json.UserAccountJson;
import fastvagas.util.ObjectUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinhaContaService {

    @Autowired
    PersonRepository personRepository;

    public void updateUserData(Person personReq, UserAccountJson userAccount) {
        Optional<Person> personDb = personRepository.findById(personReq.getId());
        personDb.ifPresent(person -> {
            person.setFirstName(userAccount.getFirstName());
            person.setLastName(userAccount.getLastName());
            person.setEmail(userAccount.getEmail());
            if (ObjectUtil.hasValue(userAccount.getPassword())) {
                person.setPassword(userAccount.getPassword());
            }
            personRepository.save(person);
        });
    }
}
