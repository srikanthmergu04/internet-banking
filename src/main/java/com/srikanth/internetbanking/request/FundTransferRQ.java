package com.srikanth.internetbanking.request;

public class FundTransferRQ {
    private Long acNo1;
    private int pin;
    private Long acNo2;
    private Integer amount;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Long getAcNo1() {
        return acNo1;
    }

    public void setAcNo1(Long acNo1) {
        this.acNo1 = acNo1;
    }

    public Long getAcNo2() {
        return acNo2;
    }

    public void setAcNo2(Long acNo2) {
        this.acNo2 = acNo2;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
