package com.nhnacademy.springmvc.domain;


import lombok.Getter;
import lombok.Setter;



public class Customer extends User{
    public Customer(String id, String password) {
        super(id, password);
    }
}
