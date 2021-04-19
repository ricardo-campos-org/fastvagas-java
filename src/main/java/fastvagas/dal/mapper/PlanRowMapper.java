package fastvagas.dal.mapper;

import fastvagas.dal.entity.Plan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PlanRowMapper implements RowMapper<Plan> {

    @Override
    public Plan mapRow(ResultSet resultSet, int i) throws SQLException {
        Plan plan = new Plan();
        plan.setPlan_id(resultSet.getLong(Plan.PLAN_ID));
        plan.setName(resultSet.getString(Plan.NAME));
        plan.setDescription(resultSet.getString(Plan.DESCRIPTION));
        plan.setAmount(resultSet.getBigDecimal(Plan.AMOUNT));
        plan.setPlan_type(resultSet.getString(Plan.PLAN_TYPE).charAt(0));
        plan.setCreated_at(new Date(resultSet.getTimestamp(Plan.CREATED_AT).getTime()));
        if (resultSet.getTimestamp(Plan.DISABLED_AT) != null) {
            plan.setDisabled_at(new Date(resultSet.getTimestamp(Plan.DISABLED_AT).getTime()));
        }
        return plan;
    }
}
