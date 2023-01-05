package fastvagas.rest;

import fastvagas.entity.City;
import fastvagas.repository.CityRepository;
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
@RequestMapping(value = "/app/city", produces = "application/json")
class CityRest {

  private final CityRepository cityRepository;

  @Autowired
  public CityRest(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @GetMapping(value = "/find-by-id/{id}")
  public City findById(@PathVariable Long id) {
    Optional<City> cityOptional = cityRepository.findById(id);
    if (cityOptional.isEmpty()) {
      // return 204
      return null;
    }

    return cityOptional.get();
  }

  @GetMapping(value = "/find-all-by-state/{id}")
  public List<City> findAllByState(@PathVariable Long id) {
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
