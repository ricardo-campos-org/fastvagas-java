package fastvagas.data.dao;

import fastvagas.data.entity.Contact;
import fastvagas.data.mapper.ContactRowMapper;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDao extends Dao<Contact> {

    public ContactDao(NamedParameterJdbcTemplate template) {
        super(Contact.class, template, new ContactRowMapper());
    }

    public Contact findById(Long contact_id) {
        final String query = "SELECT * "
            + "FROM " + Contact.TABLE + " "
            + "WHERE " + Contact.CONTACT_ID + "=:" + Contact.CONTACT_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Contact.CONTACT_ID, contact_id);

        return getObjectFromResult(query, params);
    }

    public List<Contact> findAll() {
        return getListFromResult("SELECT * FROM " + Contact.TABLE);
    }

    public Contact create(Contact contact) {
        String query = "INSERT INTO " + Contact.TABLE + "("
            + Contact.NAME
            + "," + Contact.EMAIL
            + "," + Contact.SUBJECT
            + "," + Contact.MESSAGE
            + "," + Contact.SENT_AT
            + ") values ("
            + ":" + Contact.NAME
            + ",:" + Contact.EMAIL
            + ",:" + Contact.SUBJECT
            + ",:" + Contact.MESSAGE
            + ",:" + Contact.SENT_AT
            + ")";

        if (executeInsert(query, getParams(contact)) == 1) {
            contact.setContact_id(getGeneratedId(Contact.CONTACT_ID));
            return contact;
        }

        return null;
    }

    public Contact update(Contact contact) {
        String query = "UPDATE " + Contact.TABLE
            + " SET " + Contact.NAME + "=:" + Contact.NAME
            + "," + Contact.EMAIL + "=:" + Contact.EMAIL
            + "," + Contact.SUBJECT + "=:" + Contact.SUBJECT
            + "," + Contact.MESSAGE + "=:" + Contact.MESSAGE
            + "," + Contact.SENT_AT + "=:" + Contact.SENT_AT
            + " WHERE " + Contact.CONTACT_ID + "=:" + Contact.CONTACT_ID;

        if (executeUpdateDelete(query, getParams(contact)) == 1) {
            return contact;
        }

        return null;
    }

    public Contact deleteById(Contact contact) {
        String query = "DELETE FROM " + Contact.TABLE
            + " WHERE " + Contact.CONTACT_ID + "=:" + Contact.CONTACT_ID;

        if (executeUpdateDelete(query, getParams(contact)) == 1) {
            return contact;
        }

        return null;
    }

    private Map<String, Object> getParams(Contact contact) {
        Map<String, Object> params = new HashMap<>();
        params.put(Contact.CONTACT_ID, contact.getContact_id());
        params.put(Contact.NAME, contact.getName());
        params.put(Contact.EMAIL, contact.getEmail());
        params.put(Contact.SUBJECT, contact.getSubject());
        params.put(Contact.MESSAGE, contact.getMessage());
        params.put(Contact.SENT_AT, DateUtil.getGmtTimestamp(contact.getSent_at()));
        return params;
    }
}
