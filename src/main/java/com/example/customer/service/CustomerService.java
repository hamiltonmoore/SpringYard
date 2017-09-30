package com.example.customer.service;
import com.example.customer.model.Customer;
import java.util.List;

public interface CustomerService {
        void add(Customer customer);
        Customer getById(String id);
        List<Customer> get();
        void update(Customer customer);
        void delete(String id);
    }
