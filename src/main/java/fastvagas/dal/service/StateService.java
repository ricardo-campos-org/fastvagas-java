package fastvagas.dal.service;

import fastvagas.dal.dao.StateDao;
import fastvagas.dal.entity.City;
import fastvagas.dal.entity.State;
import fastvagas.exception.EntityNotFoundException;
import fastvagas.exception.InUseException;
import fastvagas.exception.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateDao stateDao;

    @Autowired
    private CityService cityService;

    public State findById(Long state_id) {
        return stateDao.findById(state_id);
    }

    public State findBySiglaUf(String sigla_uf) {
        return stateDao.findBySiglaUf(sigla_uf);
    }

    public List<State> findAll() {
        return stateDao.findAll();
    }

    public State create(State state) {
        validations(state, true);

        return stateDao.create(state);
    }

    public State update(State state) {
        validations(state, false);

        return stateDao.update(state);
    }

    public State deleteById(Long state_id) {
        State state = findById(state_id);
        if (state == null) {
            throw new EntityNotFoundException(
                State.class,
                State.STATE_ID,
                String.valueOf(state_id)
            );
        }

        List<City> cities = cityService.findAllByStateId(state_id);

        if (!cities.isEmpty()) {
            throw new InUseException(
                "Não foi possível deletar o estado: estado em uso",
                "State in use by " + cities.size() + " city(ies)"
            );
        }

        return stateDao.deleteById(state);
    }

    private void validations(State state, boolean create) {
        if (!create) {
            if (state.getState_id() == null || state.getState_id() == 0) {
                throw new InvalidFieldException(
                    "Problema ao alterar estado: id não informado",
                    "Field " + State.STATE_ID + " must not be empty"
                );
            } else {
                State stateBd = findById(state.getState_id());
                if (stateBd == null) {
                    throw new EntityNotFoundException(
                        State.class,
                        State.STATE_ID,
                        String.valueOf(state.getState_id())
                    );
                }
            }
        }

        if (state.getName() == null || state.getName().trim().isEmpty()) {
            throw new InvalidFieldException(
                "Problema ao " + (create? "cadastrar" : "alterar") + " estado: nome não informado",
                "Field " + State.NAME + " must not be empty"
            );
        }
    }
}
