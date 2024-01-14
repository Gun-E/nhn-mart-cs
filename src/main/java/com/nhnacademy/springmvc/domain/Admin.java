package com.nhnacademy.springmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



public class Admin extends User{
    public Admin(String id, String password, String adminName) {
        super(id, password);
        this.adminName = adminName;
    }
    @Getter
    String adminName;
}