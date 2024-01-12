package com.nhnacademy.springmvc.repository;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);

}
