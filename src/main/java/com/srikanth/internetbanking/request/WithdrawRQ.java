package com.srikanth.internetbanking.request;

public class WithdrawRQ {
    private Long acNo;
    private Integer amount;
    private int pin;

    public Long getAcNo() {
        return acNo;
    }

    public void setAcNo(Long acNo) {
        this.acNo = acNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
