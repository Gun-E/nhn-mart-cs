package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Admin;

public interface AdminRepository extends UserRepository{
    void addAdmin(String id, String password, String adminName);

    Admin getAdmin(String id);

    String getAdminName(String id);
}
