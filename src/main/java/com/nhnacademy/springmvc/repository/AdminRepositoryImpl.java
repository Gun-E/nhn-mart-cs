package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Admin;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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

    public void addAdmin(String id, String password) {
        Admin admin = new Admin(id, password);
        adminMap.put(id, admin);
    }

    public Admin getAdmin(String id) {
        return exists(id) ? adminMap.get(id) : null;
    }
}
