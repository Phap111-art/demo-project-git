package com.example.projectdemogit.service;

import com.example.projectdemogit.entity.Customer;
import com.example.projectdemogit.response.ResponseCustomData;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    ResponseCustomData getAllCustomers();
    ResponseCustomData getCustomerById(Long id);
    ResponseCustomData createCustomer(Customer customer);
    ResponseCustomData updateCustomer(Long id, Customer updatedCustomer);
    ResponseCustomData deleteCustomer(Long id);
}