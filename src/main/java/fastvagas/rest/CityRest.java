package fastvagas.rest;

import fastvagas.data.entity.City;
import fastvagas.data.repository.CityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/app/city")
class CityRest {

    @Resource
    private CityService cityService;

    @GetMapping(value = "/find-by-id/{id}")
    public City findById(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @GetMapping(value = "/find-all-by-state/{id}")
    public List<City> findAllByState(@PathVariable("id") Long id) {
        return cityService.findAllByStateId(id);
    }

    @GetMapping(value = "/find-all")
    public List<City> findAll() {
        return cityService.findAll();
    }

    @PostMapping(value = "/create")
    public City create(@RequestBody City city) {
        return cityService.create(city);
    }

    @PutMapping(value = "/update")
    public City update(@RequestBody City city) {
        return cityService.update(city);
    }

    @DeleteMapping(value = "/delete-by-id")
    public City deleteById(@RequestBody City city) {
        return cityService.deleteById(city.getCity_id());
    }
}
