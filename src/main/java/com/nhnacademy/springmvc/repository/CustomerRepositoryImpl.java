package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Customer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> customerMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return customerMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getCustomer(id))
                .map(customer -> customer.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public void addCustomer(String id, String password) {
        Customer customer = new Customer(id, password);
        customerMap.put(id, customer);
    }

    @Override
    public Customer getCustomer(String id) {
        return exists(id) ? customerMap.get(id) : null;
    }
}
