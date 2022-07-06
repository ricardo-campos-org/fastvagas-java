package fastvagas.service;

import fastvagas.data.entity.City;
import fastvagas.data.entity.Person;
import fastvagas.data.entity.State;
import fastvagas.data.repository.StateRepository;
import fastvagas.data.repository.CityRepository;
import fastvagas.json.UserAccountJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    public BaseService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public UserAccountJson getCurrentUser(Person person) {
        if (person == null) {
            return null;
        }

        City city = cityRepository.findById(person.getCityId()).orElse(new City());
        State state = stateRepository.findById(city.getState().getId()).orElse(new State());

        UserAccountJson userAccountJson = new UserAccountJson();
        userAccountJson.setPersonId(person.getId());
        userAccountJson.setFirstName(person.getFirstName());
        userAccountJson.setLastName(person.getLastName());
        userAccountJson.setEmail(person.getEmail());
        userAccountJson.setCityId(person.getCityId());
        userAccountJson.setCreatedAt(person.getCreatedAt());
        userAccountJson.setLastLogin(person.getLastLogin());
        userAccountJson.setCityName(city.getName());
        userAccountJson.setStateName(state.getName());
        return userAccountJson;
    }
}
