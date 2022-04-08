package fastvagas.jpa;

import fastvagas.data.entity.CrowlerLog;
import fastvagas.util.DateUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class CrowlerLogRepositoryBean implements CrowlerLogRepository {

    public List<CrowlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime) {
        // FIXME keep from here
        return new ArrayList<>();
    }

    @Override
    public List<CrowlerLog> fromStringArray(String[] logs, Long portal_id) {
        List<CrowlerLog> crowlerLogs = new ArrayList<>(logs.length);
        for (String log : logs) {
            CrowlerLog crowlerLog = CrowlerLog.builder()
                    .created_at(DateUtil.getCurrentLocalDate())
                    .portal_id(portal_id)
                    .text(log)
                    .build();
            crowlerLogs.add(crowlerLog);
        }

        return crowlerLogs;
    }
}
