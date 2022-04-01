package fastvagas.data.entity;

import fastvagas.util.DateUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class UserJob {
    public static final String TABLE = "user_jobs";
    public static final String USER_ID = "user_id";
    public static final String PORTAL_JOB_ID = "portal_job_id";
    public static final String SEEN = "seen";

    private Long user_id;
    private Long portal_job_id;
    private LocalDateTime seen;

    @Override
    public String toString() {
        return "user_job={"
            + USER_ID + "=" + user_id
            + "," + PORTAL_JOB_ID + "=" + portal_job_id
            + "," + SEEN + "=" + DateUtil.formatLocalDateTime(seen)
            + "}";
    }
}
