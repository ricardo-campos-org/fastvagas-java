package fastvagas.dal.entity;

import fastvagas.util.DateUtil;

import java.util.Date;
import java.util.Objects;

public class Contact {
    public static final String TABLE = "contacts";
    public static final String CONTACT_ID = "contact_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String SUBJECT = "subject";
    public static final String MESSAGE = "message";
    public static final String SENT_AT = "sent_at";

    private Long contact_id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private Date sent_at;

    public Long getContact_id() {
        return contact_id;
    }

    public void setContact_id(Long contact_id) {
        this.contact_id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSent_at() {
        return sent_at;
    }

    public void setSent_at(Date sent_at) {
        this.sent_at = sent_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contact_id.equals(contact.contact_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact_id);
    }

    @Override
    public String toString() {
        return "contact={"
            + CONTACT_ID + "=" + contact_id
            + "," + NAME + "='" + name + "'"
            + "," + EMAIL + "='" + email + "'"
            + "," + SUBJECT + "='" + subject + "'"
            + "," + MESSAGE + "='" + message + "'"
            + "," + SENT_AT + "='" + DateUtil.formatDate(sent_at, true)
            + "}";
    }
}
