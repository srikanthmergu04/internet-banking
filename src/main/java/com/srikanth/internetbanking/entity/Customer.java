package com.srikanth.internetbanking.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long acNo;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String city;

    @Column
    private Integer balance;

    @Column
    private Integer pin;


    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Customer.class)
    private List<Customer> beneficiary;


    public List<Customer> getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(List<Customer> beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Long getAcNo() {
        return acNo;
    }

    public void setAcNo(Long acNo) {
        this.acNo = acNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }


}
