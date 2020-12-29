package com.srikanth.internetbanking.request;

public class DepositRQ {
    private Long acNo;
    private Integer amount;

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
}
