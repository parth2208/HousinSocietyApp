package com.example.housingsocietyapp.Model.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payments_table")
public class Payments {

    @PrimaryKey
    @NonNull
    private int paymentId;
    private String billType;
    private String paymentCycle;
    private int paymentAmount;
    private String paymentTimeStamp;
    private boolean success;
    private String paymentType;
    private String remarks;

    public Payments(String billType, String paymentCycle, int paymentAmount, String remarks,int paymentId) {
        this.billType = billType;
        this.paymentId = paymentId;
        this.paymentCycle = paymentCycle;
        this.paymentAmount = paymentAmount;
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentTimeStamp() {
        return paymentTimeStamp;
    }

    public void setPaymentTimeStamp(String paymentTimeStamp) {
        this.paymentTimeStamp = paymentTimeStamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
