package fastvagas.dal.service;

import fastvagas.dal.dao.UserDao;
import fastvagas.dal.entity.City;
import fastvagas.dal.entity.User;
import fastvagas.exception.EntityNotFoundException;
import fastvagas.exception.InUseException;
import fastvagas.exception.InvalidEmailException;
import fastvagas.exception.InvalidFieldException;
import fastvagas.util.MailUtil;
import fastvagas.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityService cityService;

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public User findByEmail(String email) {
        try {
            MailUtil.validateEmailAddress(email);
        } catch (AddressException adre) {
            throw new InvalidEmailException(
                "E-mail inválido!",
                adre,
                adre.getLocalizedMessage()
            );
        }

        return userDao.findByEmail(email);
    }

    public List<User> findAll() {
        return userDao.findAll(false);
    }

    public List<User> findAll(boolean includeDisabled) {
        return userDao.findAll(includeDisabled);
    }

    public List<User> findAllByCityId(Long city_id) {
        return userDao.findAllByCityId(city_id);
    }

    public User create(User user) {
        validations(user, true);

        user.setPassword(PasswordUtil.getSaltedHash(user.getPassword()));
        user.setCreated_at(new Date());
        user.setDisabled_at(null);
        return userDao.create(user);
    }

    public User update(User user) {
        validations(user, false);

        user.setDisabled_at(null);
        return userDao.update(user);
    }

    public User disableById(Long id) {
        User user = findById(id);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }

        return userDao.disable(user);
    }

    private void validations(User user, boolean create) {
        if (!create) {
            if (user.getUser_id() == null || user.getUser_id() == 0) {
                throw new InvalidFieldException(
                    "Problema ao alterar usuário: id não informado",
                    "Field " + User.USER_ID + " must not be empty"
                );
            } else {
                User userBd = findById(user.getUser_id());
                if (userBd == null) {
                    throw new EntityNotFoundException(
                        User.class,
                        User.USER_ID,
                        String.valueOf(user.getUser_id())
                    );
                }
            }
        }

        if (user.getFirst_name() == null || user.getFirst_name().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: nome não informado",
                "Field " + User.FIRST_NAME + " must not be empty"
            );
        }

        if (user.getLast_name() == null || user.getLast_name().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: sobrenome não informado",
                "Field " + User.LAST_NAME + " must not be empty"
            );
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: e-mail não informado",
                "Field " + User.EMAIL + " must not be empty"
            );
        } else {
            if (create) {
                if (findByEmail(user.getEmail()) != null) {
                    throw new InUseException(
                        "Problema ao cadatrar usuário: e-mail já cadastrado",
                        "E-mail already in use"
                    );
                }
            }

            try {
                MailUtil.validateEmailAddress(user.getEmail());
            } catch (AddressException adre) {
                throw new InvalidEmailException(
                    "Problema ao cadatrar usuário: e-mail inválido",
                    adre,
                    adre.getLocalizedMessage()
                );
            }
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: senha não informado",
                "Field " + User.PASSWORD + " must not be empty"
            );
        }

        if (user.getCity_id() == null || user.getCity_id() == 0) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " usuário: cidade não informada",
                "Field " + User.CITY_ID + " must not be empty"
            );
        } else {
            City city = cityService.findById(user.getCity_id());
            if (city == null) {
                throw new EntityNotFoundException(
                    City.class,
                    User.CITY_ID,
                    String.valueOf(user.getCity_id())
                );
            }
        }
    }
}
