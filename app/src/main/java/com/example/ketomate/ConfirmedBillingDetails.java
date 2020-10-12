package com.example.ketomate;

public class ConfirmedBillingDetails {

    String ordCha,deliCha,totCha,payMethod;

    public ConfirmedBillingDetails() {
    }

    public ConfirmedBillingDetails(String ordCha, String deliCha, String totCha, String payMethod) {
        this.ordCha = ordCha;
        this.deliCha = deliCha;
        this.totCha = totCha;
        this.payMethod = payMethod;
    }

    public String getOrdCha() {
        return ordCha;
    }

    public void setOrdCha(String ordCha) {
        this.ordCha = ordCha;
    }

    public String getDeliCha() {
        return deliCha;
    }

    public void setDeliCha(String deliCha) {
        this.deliCha = deliCha;
    }

    public String getTotCha() {
        return totCha;
    }

    public void setTotCha(String totCha) {
        this.totCha = totCha;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
