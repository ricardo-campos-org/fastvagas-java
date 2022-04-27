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

        City city = cityRepository.findById(person.getCity_id()).orElse(new City());
        State state = stateRepository.findById(city.getState().getId()).orElse(new State());

        UserAccountJson userAccountJson = new UserAccountJson();
        userAccountJson.setUserId(person.getUser_id());
        userAccountJson.setFirstName(person.getFirst_name());
        userAccountJson.setLastName(person.getLast_name());
        userAccountJson.setEmail(person.getEmail());
        userAccountJson.setCityId(person.getCity_id());
        userAccountJson.setCreatedAt(person.getCreated_at());
        userAccountJson.setLastLogin(person.getLast_login());
        userAccountJson.setCityName(city.getName());
        userAccountJson.setStateName(state.getName());
        return userAccountJson;
    }
}
