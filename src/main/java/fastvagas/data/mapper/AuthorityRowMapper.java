package fastvagas.data.mapper;

import fastvagas.data.entity.Authority;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorityRowMapper implements RowMapper<Authority> {

    @Override
    public Authority mapRow(ResultSet resultSet, int i) throws SQLException {
        Authority authority = new Authority();
        authority.setEmail(resultSet.getString(Authority.EMAIL));
        authority.setAuthority(resultSet.getString(Authority.AUTHORITY));
        return authority;
    }
}
