package com.nhnacademy.springmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public abstract class User {
    String id;
    String password;
}
