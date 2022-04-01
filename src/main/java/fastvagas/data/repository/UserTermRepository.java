package fastvagas.data.repository;

import fastvagas.data.entity.UserTerm;

import java.util.List;

public interface UserTermRepository {

    List<UserTerm> findAllByUserId(Long user_id);
}
