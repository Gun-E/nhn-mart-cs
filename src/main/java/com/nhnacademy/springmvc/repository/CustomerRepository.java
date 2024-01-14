package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Customer;

public interface CustomerRepository extends UserRepository{
    void addCustomer(String id, String password);

    Customer getCustomer(String id);
}
