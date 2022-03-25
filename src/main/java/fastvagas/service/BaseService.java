package fastvagas.service;

import fastvagas.data.entity.City;
import fastvagas.data.entity.State;
import fastvagas.data.entity.User;
import fastvagas.data.repository.CityService;
import fastvagas.data.repository.StateService;
import fastvagas.json.UserAccountJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    CityService cityService;

    @Autowired
    StateService stateService;

    public UserAccountJson getCurrentUser(User user) {
        if (user == null) {
            return null;
        }

        City city = cityService.findById(user.getCity_id());
        if (city == null) {
            city = new City();
        }

        State state = stateService.findById(city.getState_id());
        if (state == null) {
            state = new State();
        }

        UserAccountJson userAccountJson = new UserAccountJson();
        userAccountJson.setUserId(user.getUser_id());
        userAccountJson.setFirstName(user.getFirst_name());
        userAccountJson.setLastName(user.getLast_name());
        userAccountJson.setEmail(user.getEmail());
        userAccountJson.setCityId(user.getCity_id());
        userAccountJson.setCreatedAt(user.getCreated_at());
        userAccountJson.setLastLogin(user.getLast_login());
        userAccountJson.setCityName(city.getName());
        userAccountJson.setStateName(state.getName());
        return userAccountJson;
    }
}
