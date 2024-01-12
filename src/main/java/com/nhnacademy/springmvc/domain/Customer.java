package com.nhnacademy.springmvc.domain;


import lombok.Getter;

@Getter
public class Customer extends User{
    public Customer(String id, String password) {
        super(id, password);
    }
}
