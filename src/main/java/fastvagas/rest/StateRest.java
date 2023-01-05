package fastvagas.rest;

import fastvagas.entity.State;
import fastvagas.repository.StateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/state")
class StateRest {

  @Autowired private StateRepository stateRepository;

  @GetMapping(value = "/find-by-id/{id}")
  public State findById(@PathVariable Long id) {
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
