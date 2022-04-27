package fastvagas.rest;

import fastvagas.data.entity.State;
import fastvagas.data.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app/state")
class StateRest {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping(value = "/find-by-id/{id}")
    public State findById(@PathVariable("id") Integer id) {
        Optional<State> state = stateRepository.findById(id);
        if (state.isEmpty()) {
            return null; // 204
        }

        return state.get();
    }

    @GetMapping(value = "/find-all")
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @PostMapping(value = "/create")
    public State create(@RequestBody State state) {
        return stateRepository.save(state);
    }

    @PutMapping(value = "/update")
    public State update(@RequestBody State state) {
        return stateRepository.save(state);
    }

    @DeleteMapping(value = "/delete-by-id")
    public void deleteById(@RequestBody State state) {
        stateRepository.deleteById(state.getId());
    }
}
