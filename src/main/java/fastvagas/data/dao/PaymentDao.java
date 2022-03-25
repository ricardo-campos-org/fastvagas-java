package fastvagas.data.dao;

import fastvagas.data.entity.Payment;
import fastvagas.data.mapper.PaymentRowMapper;
import fastvagas.util.DateUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDao extends Dao<Payment> {

    public PaymentDao(NamedParameterJdbcTemplate template) {
        super(Payment.class, template, new PaymentRowMapper());
    }

    public Payment findById(Long payment_id) {
        final String query = "SELECT * FROM " + Payment.TABLE
            + " WHERE " + Payment.PAYMENT_ID + "=:" + Payment.PAYMENT_ID;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue(Payment.PAYMENT_ID, payment_id);

        return getObjectFromResult(query, params);
    }

    public List<Payment> findAll() {
        return getListFromResult("SELECT * FROM " + Payment.TABLE);
    }

    public Payment create(Payment payment) {
        final String query = "INSERT INTO " + Payment.TABLE + "("
            + Payment.USER_ID
            + "," + Payment.AMOUNT
            + "," + Payment.DUE_DATE
            + "," + Payment.PAYDAY
            + ") values ("
            + ":" + Payment.USER_ID
            + ",:" + Payment.AMOUNT
            + ",:" + Payment.DUE_DATE
            + ",:" + Payment.PAYDAY
            + ")";

        if (executeInsert(query, getParams(payment)) == 1) {
            payment.setPayment_id(getGeneratedId(Payment.PAYMENT_ID));
            return payment;
        }

        return null;
    }

    public Payment update(Payment payment) {
        final String query = "UPDATE " + Payment.TABLE
            + " SET " + Payment.USER_ID + "=:" + Payment.USER_ID
            + "," + Payment.AMOUNT + "=:" + Payment.AMOUNT
            + "," + Payment.DUE_DATE + "=:" + Payment.DUE_DATE
            + "," + Payment.PAYDAY + "=:" + Payment.PAYDAY
            + " WHERE " + Payment.PAYMENT_ID + "=:" + Payment.PAYMENT_ID;

        if (executeUpdateDelete(query, getParams(payment)) == 1) {
            return payment;
        }

        return null;
    }

    public Payment deleteById(Payment payment) {
        final String query = "DELETE FROM " + Payment.TABLE
            + " WHERE " + Payment.PAYMENT_ID + "=:" + Payment.PAYMENT_ID;

        if (executeUpdateDelete(query, getParams(payment)) == 1) {
            return payment;
        }

        return null;
    }

    private Map<String, Object> getParams(Payment payment) {
        Map<String, Object> params = new HashMap<>();
        params.put(Payment.USER_ID, payment.getUser_id());
        params.put(Payment.AMOUNT, payment.getAmount());
        params.put(Payment.DUE_DATE, DateUtil.getGmtTimestamp(payment.getDue_date()));
        params.put(Payment.PAYDAY, DateUtil.getGmtTimestamp(payment.getPayday()));
        return params;
    }
}
