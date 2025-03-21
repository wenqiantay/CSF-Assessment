package vttp.batch5.csf.assessment.server.models;

public class Payment {
    
    private String orderId;
    private String payer;
    private String payee = "Tay Wenqian";
    private double payment;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getPayer() {
        return payer;
    }
    public void setPayer(String payer) {
        this.payer = payer;
    }
    public String getPayee() {
        return payee;
    }
    public void setPayee(String payee) {
        this.payee = payee;
    }
    public double getPayment() {
        return payment;
    }
    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    @Override
    public String toString() {
        return "Payment [orderId=" + orderId + ", payer=" + payer + ", payee=" + payee + ", payment=" + payment + "]";
    }

    
}
