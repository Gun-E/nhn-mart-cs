package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.post.Type;
import com.nhnacademy.springmvc.repository.*;
import com.nhnacademy.springmvc.repository.post.PostRepository;
import com.nhnacademy.springmvc.repository.post.PostRepositoryImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public CustomerRepository customerRepository() {
        CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl();
        customerRepositoryImpl.addCustomer("qwe", "qwe");

        return customerRepositoryImpl;
    }
    @Bean
    public AdminRepository adminRepository() {
        AdminRepositoryImpl adminRepositoryImpl = new AdminRepositoryImpl();
        adminRepositoryImpl.addAdmin("admin", "12345", "CS 관리자");

        return adminRepositoryImpl;
    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");

        return messageSource;
    }
}
