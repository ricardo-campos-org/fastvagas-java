package fastvagas.data.entity;

import fastvagas.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CrowlerLog {

    public static final String TABLE = "crowler_log";
    public static final String CREATED_AT = "created_at";
    public static final String SEQUENCE = "sequence";
    public static final String PORTAL_ID = "portal_id";
    public static final String TEXT = "text";

    private LocalDate created_at;
    private Integer sequence;
    private Long portal_id;
    private String text;

    @Override
    public String toString() {
        return "crowler_log={"
            + CREATED_AT + "='" + DateUtil.formatLocalDate(created_at) + "'"
            + "," + SEQUENCE + "=" + sequence
            + "," + PORTAL_ID + "=" + portal_id
            + "," + TEXT + "='" + text + "'"
            + "}";
    }
}
