package vttp.batch5.csf.assessment.server.repositories;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.csf.assessment.server.models.Receipt;

// Use the following class for MySQL database
@Repository
public class RestaurantRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_QUERY = "SELECT COUNT(*) FROM customers WHERE username = ? AND password = ?";
    private final String SQL_INSERT = "INSERT into place_orders(order_id, payment_id, order_date, total, username) VALUES(?, ?, ?, ?, ?)";

    public boolean validateCreditential(String username, String password) {


        Integer count = jdbcTemplate.queryForObject(SQL_QUERY, Integer.class, username, password);

        return count != null && count > 0;

    }

    public void insertOrder(Receipt receipt, String payer, double total) {

        Date date = new Date(receipt.getTimestamp());

        jdbcTemplate.update(SQL_INSERT, receipt.getOrderId(), receipt.getPaymentId(), date, total, payer);

    }

}
