package com.example.ketomate;

public class ConfirmedBillingDetails {

    String ordCharge,deliCharge,total,payMethod;

    public ConfirmedBillingDetails() {
    }

    public ConfirmedBillingDetails(String ordCharge, String deliCharge, String total, String payMethod) {
        this.ordCharge = ordCharge;
        this.deliCharge = deliCharge;
        this.total = total;
        this.payMethod = payMethod;
    }

    public String getOrdCharge() {
        return ordCharge;
    }

    public void setOrdCharge(String ordCharge) {
        this.ordCharge = ordCharge;
    }

    public String getDeliCharge() {
        return deliCharge;
    }

    public void setDeliCharge(String deliCharge) {
        this.deliCharge = deliCharge;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
