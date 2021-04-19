package fastvagas.dal.mapper;

import fastvagas.dal.entity.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaymentRowMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
        Payment payment = new Payment();
        payment.setPayment_id(resultSet.getLong(Payment.PAYMENT_ID));
        payment.setUser_id(resultSet.getLong(Payment.USER_ID));
        payment.setAmount(resultSet.getBigDecimal(Payment.AMOUNT));
        if (resultSet.getTimestamp(Payment.DUE_DATE) != null) {
            payment.setDue_date(new Date(resultSet.getTimestamp(Payment.DUE_DATE).getTime()));
        }
        if (resultSet.getTimestamp(Payment.PAYDAY) != null) {
            payment.setPayday(new Date(resultSet.getTimestamp(Payment.PAYDAY).getTime()));
        }
        return payment;
    }
}
