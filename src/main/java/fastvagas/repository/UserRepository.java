package fastvagas.repository;

import fastvagas.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/** This class holds methods to interact with user database. */
public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAllByDisabledAt(LocalDateTime localDateTime);
}
