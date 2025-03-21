package vttp.batch5.csf.assessment.server.models;

import java.util.Date;
import java.util.List;

public class Order {
    
    private String orderId;
    private String username;
    private double total;
    private Date timeStamp;
    private List<OrderItem> items;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", username=" + username + ", total=" + total + ", timeStamp=" + timeStamp
                + ", items=" + items + "]";
    }
    
}
