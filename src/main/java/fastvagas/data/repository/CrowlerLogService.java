package fastvagas.data.repository;

import fastvagas.data.dao.CrowlerLogDao;
import fastvagas.data.entity.CrowlerLog;
import fastvagas.util.DateUtil;
import fastvagas.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrowlerLogService {

    @Autowired
    private CrowlerLogDao crowlerLogDao;

    public Integer getNextSequence() {
        return crowlerLogDao.getNextSequence();
    }

    public List<CrowlerLog> createBatch(List<CrowlerLog> list) {
        return crowlerLogDao.createBatch(list);
    }

    public List<CrowlerLog> findAllByPrimaryKey(LocalDate created_at, Integer sequence) {
        return crowlerLogDao.findAllByPrimaryKey(created_at, sequence);
    }

    public List<CrowlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime) {
        return crowlerLogDao.findAllByGreaterDateTime(localDateTime);
    }

    public List<CrowlerLog> fromStringArray(String[] logs, int nextSequence, Long portal_id) {
        List<CrowlerLog> crowlerLogs = new ArrayList<>(logs.length);
        for (String log : logs) {
            CrowlerLog crowlerLog = CrowlerLog.builder()
                    .created_at(DateUtil.getCurrentLocalDate())
                    .sequence(nextSequence++)
                    .portal_id(portal_id)
                    .text(log)
                    .build();
            crowlerLogs.add(crowlerLog);
        }

        return crowlerLogs;
    }
}
