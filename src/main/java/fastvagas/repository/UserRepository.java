package fastvagas.repository;

import fastvagas.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** This class holds methods to interact with user database. */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findAllByDisabledAt(LocalDateTime localDateTime);

  List<UserEntity> findAllByEmail(String email);
}
