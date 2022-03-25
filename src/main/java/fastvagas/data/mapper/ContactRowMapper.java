package fastvagas.data.mapper;

import fastvagas.data.entity.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setContact_id(resultSet.getLong(Contact.CONTACT_ID));
        contact.setName(resultSet.getString(Contact.NAME));
        contact.setEmail(resultSet.getString(Contact.EMAIL));
        contact.setSubject(resultSet.getString(Contact.SUBJECT));
        contact.setMessage(resultSet.getString(Contact.MESSAGE));
        contact.setSent_at(new Date(resultSet.getTimestamp(Contact.SENT_AT).getTime()));
        return contact;
    }
}
