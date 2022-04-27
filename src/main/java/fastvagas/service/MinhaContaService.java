package fastvagas.service;

import fastvagas.data.entity.Person;
import fastvagas.json.UserAccountJson;
import fastvagas.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinhaContaService {

    @Autowired
    UserService userService;

    public void updateUserData(Person person, UserAccountJson userAccount) {
        person.setFirst_name(userAccount.getFirstName());
        person.setLast_name(userAccount.getLastName());
        person.setEmail(userAccount.getEmail());
        if (ObjectUtil.hasValue(userAccount.getPassword())) {
            person.setPassword(userAccount.getPassword());
        }

        userService.update(person);
    }
}
