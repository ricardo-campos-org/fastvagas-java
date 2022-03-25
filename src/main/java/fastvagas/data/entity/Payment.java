package fastvagas.data.entity;

import fastvagas.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Payment {
    public static final String TABLE = "payments";
    public static final String PAYMENT_ID = "payment_id";
    public static final String USER_ID = "user_id";
    public static final String AMOUNT = "amount";
    public static final String DUE_DATE = "due_date";
    public static final String PAYDAY = "payday";

    private Long payment_id;
    private Long user_id;
    private BigDecimal amount;
    private Date due_date;
    private Date payday;

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return payment_id.equals(payment.payment_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_id);
    }

    @Override
    public String toString() {
        return "payment={"
            + PAYMENT_ID + "=" + payment_id
            + "," + USER_ID + "=" + user_id
            + "," + AMOUNT + "=" + amount
            + "," + DUE_DATE + "='" + DateUtil.formatDate(due_date) + "'"
            + "," + PAYDAY + "='" + DateUtil.formatDate(payday, true) + "'"
            + "}";
    }
}
