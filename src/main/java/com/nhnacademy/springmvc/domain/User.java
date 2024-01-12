package com.nhnacademy.springmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public abstract class User {
    String id;
    String password;
}
