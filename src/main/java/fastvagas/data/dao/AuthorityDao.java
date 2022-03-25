package fastvagas.data.dao;

import fastvagas.data.entity.Authority;
import fastvagas.data.mapper.AuthorityRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorityDao extends Dao<Authority> {

    public AuthorityDao(NamedParameterJdbcTemplate template) {
        super(Authority.class,template, new AuthorityRowMapper());
    }

    public Authority create(Authority authority) {
        String query = "INSERT INTO " + Authority.TABLE + "("
                + Authority.EMAIL
                + "," + Authority.AUTHORITY
                + ") values ("
                + ":" + Authority.EMAIL
                + ",:" + Authority.AUTHORITY
                + ")";

        if (executeInsert(query, getParams(authority)) == 1) {
            return authority;
        }

        return null;
    }

    private Map<String, Object> getParams(Authority authority) {
        Map<String, Object> params = new HashMap<>();
        params.put(Authority.EMAIL, authority.getEmail());
        params.put(Authority.AUTHORITY, authority.getAuthority());
        return params;
    }
}
