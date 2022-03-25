package fastvagas.data.repository;

import fastvagas.data.dao.CityDao;
import fastvagas.data.entity.City;
import fastvagas.data.entity.State;
import fastvagas.data.entity.User;
import fastvagas.exception.EntityNotFoundException;
import fastvagas.exception.InUseException;
import fastvagas.exception.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StateService stateService;

    @Autowired
    private UserService userService;

    public City findById(Long city_id) {
        return cityDao.findById(city_id);
    }

    public List<City> findAll() {
        return cityDao.findAll();
    }

    public List<City> findAllByStateId(Long state_id) {
        return cityDao.findAllByStateId(state_id);
    }

    public List<City> findAllByStateSigla(String sigla) {
        State state = stateService.findBySiglaUf(sigla);
        if (state == null) {
            return new ArrayList<>();
        }
        return cityDao.findAllByStateId(state.getState_id());
    }

    public City create(City city) {
        validations(city, true);

        return cityDao.create(city);
    }

    public City update(City city) {
        validations(city, false);

        return cityDao.update(city);
    }

    public City deleteById(Long city_id) {
        City city = findById(city_id);
        if (city == null) {
            throw new EntityNotFoundException(City.class, "city_id", String.valueOf(city_id));
        }

        List<User> users = userService.findAllByCityId(city.getCity_id());
        if (!users.isEmpty()) {
            throw new InUseException(
                "Não foi possível deletar a cidade: cidade em uso",
                "City in use by " + users.size() + " user(s)"
            );
        }

        // TODO: implement the same for class Portal

        return cityDao.deleteById(city);
    }

    public Boolean validateStateCity(String siglaUf, Long city_id) {
        List<City> cities = findAllByStateSigla(siglaUf);
        if (cities.isEmpty()) {
            return Boolean.FALSE;
        }

        City city = findById(city_id);
        if (city == null) {
            return Boolean.FALSE;
        }

        return cities.contains(city);
    }

    private void validations(City city, boolean create) {
        if (!create) {
            if (city.getCity_id() == null || city.getCity_id() == 0) {
                throw new InvalidFieldException(
                    "Problema ao alterar cidade: id não informado",
                    "Field " + City.CITY_ID + " must not be empty"
                );
            } else {
                City cityBd = findById(city.getCity_id());
                if (cityBd == null) {
                    throw new EntityNotFoundException(
                        City.class,
                        City.CITY_ID,
                        String.valueOf(city.getCity_id())
                    );
                }
            }
        }

        if (city.getName() == null || city.getName().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " cidade: nome não informado",
                "Field " + City.NAME + " must not be empty"
            );
        }

        if (city.getState_id() == null || city.getState_id() == 0) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " cidade: estado não informado",
                "Field " + City.STATE_ID + " must not be empty"
            );
        } else {
            State state = stateService.findById(city.getState_id());
            if (state == null) {
                throw new EntityNotFoundException(State.class, State.STATE_ID, String.valueOf(city.getState_id()));
            }
        }
    }
}
