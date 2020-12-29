package com.srikanth.internetbanking.controller;

import com.srikanth.internetbanking.entity.Customer;
import com.srikanth.internetbanking.request.DepositRQ;
import com.srikanth.internetbanking.request.FundTransferRQ;
import com.srikanth.internetbanking.request.WithdrawRQ;
import com.srikanth.internetbanking.service.Impl.CustomerServiceImplimentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    CustomerServiceImplimentation customerServiceImplimentation;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        customerServiceImplimentation.addCustomer(customer);
        return customer;
    }


    @GetMapping("/balanceEnquiry/{acNo}")
    public Integer balanceEnquiry(@PathVariable Long acNo) {
        return customerServiceImplimentation.getBalByAcNumber(acNo);
    }

    @PostMapping("/deposit")
    public Integer deposit(@RequestBody DepositRQ depositRQ) {
        return customerServiceImplimentation.deposit(depositRQ);
    }

    @PostMapping("/withdraw")
    public Integer withdraw(@RequestBody WithdrawRQ withdrawRQ) {
        return customerServiceImplimentation.withdraw(withdrawRQ);
    }

    @PostMapping("/fundTransfer")
    public String fundTransfer(@RequestBody FundTransferRQ fundTransferRQ) {

        boolean status = customerServiceImplimentation.verifyCustomer(fundTransferRQ.getAcNo1(), fundTransferRQ.getPin());

        if (status == true) {
            WithdrawRQ withdrawRQ = new WithdrawRQ();
            withdrawRQ.setAcNo(fundTransferRQ.getAcNo1());
            withdrawRQ.setAmount(fundTransferRQ.getAmount());
            withdrawRQ.setPin(fundTransferRQ.getPin());
            int remAmount = customerServiceImplimentation.withdraw(withdrawRQ);
            if (remAmount == -1) {
                return "FundTransfer Failed.";
            } else {
                DepositRQ depositRQ = new DepositRQ();
                depositRQ.setAcNo(fundTransferRQ.getAcNo2());
                depositRQ.setAmount(fundTransferRQ.getAmount());
                customerServiceImplimentation.deposit(depositRQ);
                return "FundTransfer Successful.";
            }
        } else {
            return "FundTransfer Failed.";
        }
    }

    @GetMapping("/getCustomers")
    public List<Customer> getCustomerList(Model model) {
        return customerServiceImplimentation.listAllCustomers();
    }

//    @RequestMapping("/addBeneficiary")
//    public String addBeneficiary(HttpServletRequest req, Model model) {
//        int acNo1 = Integer.parseInt(req.getParameter("acNo1"));
//        int acNo2 = Integer.parseInt(req.getParameter("acNo2"));
//
//        System.out.println("acNO1 =  " + acNo1 + "  acNO2 = " + acNo2);
//
//        customerServiceImplimentation.addBeneficiary(acNo1, acNo2);
//
//        return "beneficiarysuccess.jsp";
//
//    }
//
//    @RequestMapping("/showBeneficiaryList")
//    public String showBeneficiaryList(HttpServletRequest req, Model m) {
//        int acNo = Integer.parseInt(req.getParameter("acNo"));
//
//        System.out.println("acNO = " + acNo);
//
//
//        Customer customer = customerServiceImplimentation.showBeneficiaryList(acNo);
//
//        List<Customer> list = customer.getBeneficiary();
//
//        Long acNum = customer.getAcNo();
//
//        m.addAttribute("acNum", acNum);
//
//        m.addAttribute("BeneficiaryList", list);
//        return "displayBeneficiaryList.jsp";
//
//    }
}
