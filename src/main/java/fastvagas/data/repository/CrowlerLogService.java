package fastvagas.data.repository;

import fastvagas.data.dao.CrowlerLogDao;
import fastvagas.data.entity.CrowlerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrowlerLogService {

    @Autowired
    private CrowlerLogDao crowlerLogDao;

    public Integer getLastSequenceByDate(LocalDate pDate) {
        return crowlerLogDao.getLastSequenceByDate(pDate);
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
}
