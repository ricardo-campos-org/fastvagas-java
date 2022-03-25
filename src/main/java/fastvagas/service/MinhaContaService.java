package fastvagas.service;

import fastvagas.data.entity.User;
import fastvagas.data.repository.UserService;
import fastvagas.json.UserAccountJson;
import fastvagas.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinhaContaService {

    @Autowired
    UserService userService;

    public void updateUserData(User user, UserAccountJson userAccount) {
        user.setFirst_name(userAccount.getFirstName());
        user.setLast_name(userAccount.getLastName());
        user.setEmail(userAccount.getEmail());
        if (ObjectUtil.hasValue(userAccount.getPassword())) {
            user.setPassword(userAccount.getPassword());
        }

        userService.update(user);
    }
}
