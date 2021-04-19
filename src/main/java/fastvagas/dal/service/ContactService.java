package fastvagas.dal.service;

import fastvagas.dal.dao.ContactDao;
import fastvagas.dal.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    public Contact create(Contact contact) {
        contact.setSent_at(new Date());
        return contactDao.create(contact);
    }
}
