package com.srikanth.internetbanking.service;

import com.srikanth.internetbanking.entity.Customer;
import com.srikanth.internetbanking.request.DepositRQ;
import com.srikanth.internetbanking.request.WithdrawRQ;

import java.util.List;


public interface CustomerService {

    public int addCustomer(Customer customer);

    public Integer getBalByAcNumber(Long acNo);

    public Integer withdraw(WithdrawRQ withdrawRQ);

    public boolean verifyCustomer(Long acNo, int pin);

    public Integer deposit(DepositRQ depositRQ);

    public List<Customer> listAllCustomers();

//    public void addBeneficiary(Long acNo1, Long acNo2);
//
//    public Customer showBeneficiaryList(Long acNo);

}
