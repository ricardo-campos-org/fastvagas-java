package fastvagas.dal.service;

import fastvagas.dal.dao.CrowlerLogDao;
import fastvagas.dal.entity.CrowlerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
}
