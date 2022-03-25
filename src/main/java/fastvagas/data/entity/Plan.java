package fastvagas.data.entity;

import fastvagas.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Plan {
    public static final String TABLE = "plans";
    public static final String PLAN_ID = "plan_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String PLAN_TYPE = "plan_type";
    public static final String CREATED_AT = "created_at";
    public static final String DISABLED_AT = "created_at";

    private Long plan_id;
    private String name;
    private String description;
    private BigDecimal amount;
    private Character plan_type;
    private Date created_at;
    private Date disabled_at;

    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Character getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(Character plan_type) {
        this.plan_type = plan_type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDisabled_at() {
        return disabled_at;
    }

    public void setDisabled_at(Date disabled_at) {
        this.disabled_at = disabled_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return plan_id.equals(plan.plan_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plan_id);
    }

    @Override
    public String toString() {
        return "plan={"
            + PLAN_ID + "=" + plan_id
            + "," + NAME + "='" + name + "'"
            + "," + DESCRIPTION + "='" + description + "'"
            + "," + AMOUNT + "=" + amount
            + "," + PLAN_TYPE + "='" + plan_type + "'"
            + "," + CREATED_AT + "='" + DateUtil.formatDate(created_at, true) + "'"
            + "," + DISABLED_AT + "='" + DateUtil.formatDate(disabled_at, true) + "'"
            + "}";
    }
}
