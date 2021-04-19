package fastvagas.dal.entity;

import fastvagas.util.DateUtil;

import java.util.Date;
import java.util.Objects;

public class CrowlerLog {

    public static final String TABLE = "crowler_log";
    public static final String CREATED_AT = "created_at";
    public static final String SEQUENCE = "sequence";
    public static final String PORTAL_ID = "portal_id";
    public static final String TEXT = "text";

    private Date created_at;
    private Integer sequence;
    private Long portal_id;
    private String text;


    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getPortal_id() {
        return portal_id;
    }

    public void setPortal_id(Long portal_id) {
        this.portal_id = portal_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrowlerLog that = (CrowlerLog) o;
        return created_at.equals(that.created_at) &&
                sequence.equals(that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created_at, sequence);
    }

    @Override
    public String toString() {
        return "crowler_log={"
            + CREATED_AT + "='" + DateUtil.formatDate(created_at, true) + "'"
            + "," + SEQUENCE + "=" + sequence
            + "," + PORTAL_ID + "=" + portal_id
            + "," + TEXT + "='" + text + "'"
            + "}";
    }
}
