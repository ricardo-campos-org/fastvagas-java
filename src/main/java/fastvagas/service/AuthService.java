package fastvagas.service;

import fastvagas.dal.entity.User;
import fastvagas.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        return userService.findByEmail(userDetails.getUsername());
    }
}
