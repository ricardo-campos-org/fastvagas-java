package fastvagas.controller;

import fastvagas.dal.entity.State;
import fastvagas.dal.service.StateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/app/state")
class StateController {

    @Resource
    private StateService stateService;

    @GetMapping(value = "/find-by-id/{id}")
    public State findById(@PathVariable("id") Long id) {
        return stateService.findById(id);
    }

    @GetMapping(value = "/find-all")
    public List<State> findAll() {
        return stateService.findAll();
    }

    @PostMapping(value = "/create")
    public State create(@RequestBody State state) {
        return stateService.create(state);
    }

    @PutMapping(value = "/update")
    public State update(@RequestBody State state) {
        return stateService.update(state);
    }

    @DeleteMapping(value = "/delete-by-id")
    public State deleteById(@RequestBody State state) {
        return stateService.deleteById(state.getState_id());
    }
}
