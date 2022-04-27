package fastvagas.rest;

import fastvagas.data.entity.City;
import fastvagas.data.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/app/city", produces = "application/json")
class CityRest {

    private final CityRepository cityRepository;

    @Autowired
    public CityRest(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = "/find-by-id/{id}")
    public City findById(@PathVariable("id") Integer id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isEmpty()) {
            // return 204
            return null;
        }

        return cityOptional.get();
    }

    @GetMapping(value = "/find-all-by-state/{id}")
    public List<City> findAllByState(@PathVariable("id") Integer id) {
        return cityRepository.findAllByStateId(id);
    }

    @GetMapping(value = "/find-all")
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @PostMapping(value = "/create")
    public City create(@RequestBody City city) {
        try {
            return cityRepository.save(city);
            // return 201
        } catch (Exception e) {
            // return 500
        }
        return null;
    }

    @PutMapping(value = "/update")
    public City update(@RequestBody City city) {
        try {
            return cityRepository.save(city);
            // return 200
        } catch (Exception e) {
            // return 500
        }
        return null;
    }

    @DeleteMapping(value = "/delete-by-id")
    public void deleteById(@RequestBody City city) {
        try {
            cityRepository.deleteById(city.getId());
            // return 200
        } catch (Exception e) {
            // return 500
        }
    }
}
