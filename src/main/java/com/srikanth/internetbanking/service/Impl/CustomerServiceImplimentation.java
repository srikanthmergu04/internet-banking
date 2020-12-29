package com.srikanth.internetbanking.service.Impl;

import com.srikanth.internetbanking.entity.Customer;
import com.srikanth.internetbanking.repository.CustomerRepository;
import com.srikanth.internetbanking.request.DepositRQ;
import com.srikanth.internetbanking.request.WithdrawRQ;
import com.srikanth.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImplimentation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public int addCustomer(Customer customer) {
        customerRepository.save(customer);
        return 1;
    }

    public Integer getBalByAcNumber(Long acNo) {

        Optional<Customer> customer = customerRepository.findById(acNo);
        if (customer.isPresent()) {
            return customer.get().getBalance();
        }
        return null;
    }

    public boolean verifyCustomer(Long acNo, int pin) {
        Optional<Customer> customer = customerRepository.findById(acNo);
        if (customer.isPresent()) {
            if (pin != customer.get().getPin()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public Integer deposit(DepositRQ depositRQ) {
        Optional<Customer> optional = customerRepository.findById(depositRQ.getAcNo());
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customer.setBalance(customer.getBalance() + depositRQ.getAmount());
            customerRepository.save(customer);
            return customer.getBalance();
        }
        return null;
    }

    public Integer withdraw(WithdrawRQ withdrawRQ) {

        boolean isValidCustomer = verifyCustomer(withdrawRQ.getAcNo(), withdrawRQ.getPin());

        if (isValidCustomer) {
            Integer balance = getBalByAcNumber(withdrawRQ.getAcNo());
            if (withdrawRQ.getAmount() > balance) {
                return -1;
            } else {
                Optional<Customer> optional = customerRepository.findById(withdrawRQ.getAcNo());
                if (optional.isPresent()) {
                    Customer customer = optional.get();
                    customer.setBalance(customer.getBalance() - withdrawRQ.getAmount());
                    customerRepository.save(customer);
                    return customer.getBalance();
                }
                return 0;
            }
        } else {
            return null;
        }
    }

    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();

    }

//    public void addBeneficiary(int acNo1, int acNo2) {
//        dao.addBeneficiary(acNo1, acNo2);
//    }
//
//    public Customer showBeneficiaryList(int acNo) {
//        Customer customer = dao.showBeneficiaryList(acNo);
//        return customer;
//    }

}
