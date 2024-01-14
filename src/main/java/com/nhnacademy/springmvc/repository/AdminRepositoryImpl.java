package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Admin;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final Map<String, Admin> adminMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return adminMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getAdmin(id))
                .map(admin -> admin.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public void addAdmin(String id, String password, String adminName) {
        Admin admin = new Admin(id, password, adminName);
        adminMap.put(id, admin);
    }

    @Override
    public Admin getAdmin(String id) {
        return exists(id) ? adminMap.get(id) : null;
    }

    @Override
    public String getAdminName(String id) {
        return getAdmin(id).getAdminName();
    }
}
