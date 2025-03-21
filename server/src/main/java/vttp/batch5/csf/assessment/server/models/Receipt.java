package vttp.batch5.csf.assessment.server.models;

public class Receipt {
    
    private String paymentId;
    private String orderId;
    private long timestamp;
    private double total;
    
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "Receipt [paymentId=" + paymentId + ", orderId=" + orderId + ", timestamp=" + timestamp + ", total="
                + total + "]";
    }
   

    
}
