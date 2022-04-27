package fastvagas.data.repository;

import fastvagas.data.entity.CrowlerLog;

import java.time.LocalDateTime;
import java.util.List;

public interface CrowlerLogCustomRepository {

    List<CrowlerLog> findAllByGreaterDateTime(LocalDateTime localDateTime);

    List<CrowlerLog> fromStringArray(String[] logs, Integer portal_id);
}
