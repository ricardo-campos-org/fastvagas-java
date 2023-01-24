package fastvagas.repository;

import fastvagas.entity.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  List<City> findAllByStateId(Long stateId);
}
