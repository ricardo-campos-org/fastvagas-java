package fastvagas.dal.entity;

import java.util.Objects;

public class UserJob {
    public static final String TABLE = "user_jobs";
    public static final String USER_ID = "user_id";
    public static final String PORTAL_JOB_ID = "portal_job_id";

    private Long user_id;
    private Long portal_job_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPortal_job_id() {
        return portal_job_id;
    }

    public void setPortal_job_id(Long portal_job_id) {
        this.portal_job_id = portal_job_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserJob userJob = (UserJob) o;
        return user_id.equals(userJob.user_id) &&
            portal_job_id.equals(userJob.portal_job_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, portal_job_id);
    }

    @Override
    public String toString() {
        return "user_job={"
            + USER_ID + "=" + user_id
            + "," + PORTAL_JOB_ID + "=" + portal_job_id
            + "}";
    }
}
