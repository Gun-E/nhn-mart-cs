package com.nhnacademy.springmvc.controller.auth;

import com.nhnacademy.springmvc.exception.LoginFailedException;
import com.nhnacademy.springmvc.repository.AdminRepository;
import com.nhnacademy.springmvc.repository.CustomerRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    public LoginController(CustomerRepository customerRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }




    @GetMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          @RequestParam("userType") String userType,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        try {
            if (adminRepository.matches(id, pwd) && Objects.equals(userType, "admin")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("adminId", id);
                return "redirect:/admin/list";
            } else if (customerRepository.matches(id, pwd) && Objects.equals(userType, "customer")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("customerId", id);
                return "redirect:/customer";
            } else {
                throw new LoginFailedException("로그인 실패");
            }
        } catch (LoginFailedException e) {
            modelMap.put("loginErrorMessage", e.getMessage());
            return "login/loginForm";
        }
    }
}
