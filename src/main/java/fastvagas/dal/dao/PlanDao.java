package fastvagas.dal.dao;

import fastvagas.dal.entity.Plan;
import fastvagas.dal.mapper.PlanRowMapper;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanDao extends Dao<Plan> {

    public PlanDao(NamedParameterJdbcTemplate template) {
        super(Plan.class, template, new PlanRowMapper());
    }

    public Plan findById(Long plan_id) {
        final String query = "SELECT * FROM " + Plan.TABLE
            + " WHERE " + Plan.PLAN_ID + "=:" + Plan.PLAN_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Plan.PLAN_ID, plan_id);

        return getObjectFromResult(query, params);
    }

    public List<Plan> findAll() {
        return getListFromResult("SELECT * FROM " + Plan.TABLE);
    }

    public Plan create(Plan plan) {
        final String query = "INSERT INTO " + Plan.TABLE + " ("
            + Plan.NAME
            + "," + Plan.DESCRIPTION
            + "," + Plan.AMOUNT
            + "," + Plan.PLAN_TYPE
            + "," + Plan.CREATED_AT
            + "," + Plan.DISABLED_AT
            + ") values ("
            + ":" + Plan.NAME
            + ",:" + Plan.DESCRIPTION
            + ",:" + Plan.AMOUNT
            + ",:" + Plan.PLAN_TYPE
            + ",:" + Plan.CREATED_AT
            + ",:" + Plan.DISABLED_AT
            + ")";

        if (executeInsert(query, getParams(plan)) == 1) {
            plan.setPlan_id(getGeneratedId(Plan.PLAN_ID));
            return plan;
        }

        return null;
    }

    public Plan update(Plan plan) {
        final String query = "UPDATE " + Plan.TABLE
            + " SET " + Plan.NAME + "=:" + Plan.NAME
            + "," + Plan.DESCRIPTION + "=:" + Plan.DESCRIPTION
            + "," + Plan.AMOUNT + "=:" + Plan.AMOUNT
            + "," + Plan.PLAN_TYPE + "=:" + Plan.PLAN_TYPE
            + "," + Plan.DISABLED_AT + "=:" + Plan.DISABLED_AT
            + " WHERE " + Plan.PLAN_ID + "=:" + Plan.PLAN_ID;

        if (executeUpdateDelete(query, getParams(plan)) == 1) {
            return plan;
        }

        return null;
    }

    public Plan disableById(Plan plan) {
        final String query = "UPDATE " + Plan.TABLE
            + " SET " + Plan.DISABLED_AT + "=:" + Plan.DISABLED_AT
            + " WHERE " + Plan.PLAN_ID + "=:" + Plan.PLAN_ID;

        if (executeUpdateDelete(query, getParams(plan)) == 1) {
            return plan;
        }

        return null;
    }

    public Map<String, Object> getParams(Plan plan) {
        Map<String, Object> params = new HashMap<>();
        params.put(Plan.PLAN_ID, plan.getPlan_id());
        params.put(Plan.NAME, plan.getName());
        params.put(Plan.DESCRIPTION, plan.getDescription());
        params.put(Plan.AMOUNT, plan.getAmount());
        params.put(Plan.PLAN_TYPE, plan.getPlan_type());
        params.put(Plan.CREATED_AT, DateUtil.getGmtTimestamp(plan.getCreated_at()));
        params.put(Plan.DISABLED_AT, DateUtil.getGmtTimestamp(plan.getDisabled_at()));
        return params;
    }
}
