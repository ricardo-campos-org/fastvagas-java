package fastvagas.dal.service;

import fastvagas.dal.dao.CrowlerLogDao;
import fastvagas.dal.entity.CrowlerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CrowlerLogService {

    @Autowired
    private CrowlerLogDao crowlerLogDao;

    public Integer getLastSequenceByDate(Date pDate) {
        return crowlerLogDao.getLastSequenceByDate(pDate);
    }

    public List<CrowlerLog> createBatch(List<CrowlerLog> list) {
        return crowlerLogDao.createBatch(list);
    }
}
