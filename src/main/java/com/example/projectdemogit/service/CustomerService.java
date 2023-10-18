package com.example.projectdemogit.service;

import com.example.projectdemogit.entity.Customer;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    boolean deleteCustomer(Long id);
}