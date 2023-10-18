package com.example.projectdemogit.service.impl;

import com.example.projectdemogit.entity.Customer;
import com.example.projectdemogit.repository.CustomerRepository;
import com.example.projectdemogit.response.ResponseCustomData;
import com.example.projectdemogit.response.ResponseCustomError;
import com.example.projectdemogit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseCustomData getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        ResponseCustomData responseObject = new ResponseCustomData("get all customer successfully !", HttpStatus.OK.value(), customers);
        return responseObject;
    }

    @Override
    public ResponseCustomData getCustomerById(Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                return new ResponseCustomData("Found Id " + id + " successfully", HttpStatus.OK.value(), customer.get());
            } else {
                throw new RuntimeException("Not found id customer: " + id);
            }
        } catch (RuntimeException e) {
            throw new ResponseCustomError(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public ResponseCustomData createCustomer(Customer customer) {
        try {
            Customer createdCustomer = customerRepository.save(customer);
            return new ResponseCustomData("Save Successfully", HttpStatus.CREATED.value(), createdCustomer);
        } catch (RuntimeException e) {
            throw new ResponseCustomError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseCustomData updateCustomer(Long id, Customer updatedCustomer) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(id);
            if (existingCustomer.isPresent()) {
                updatedCustomer.setCustomerId(id);
                Customer updatedCustomerResult = customerRepository.save(updatedCustomer);
                return new ResponseCustomData("Customer updated successfully!", HttpStatus.OK.value(), updatedCustomerResult);
            } else {
                throw new RuntimeException("Update failed! Customer not found: " + id);
            }
        } catch (RuntimeException error) {
            throw new ResponseCustomError(error.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public ResponseCustomData deleteCustomer(Long id) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(id);
            if (existingCustomer.isPresent()) {
                customerRepository.deleteById(id);
                return new ResponseCustomData("Customer deleted successfully!", HttpStatus.ACCEPTED.value(), "");
            } else {
                    throw new RuntimeException("Customer not found!");
            }
        } catch (RuntimeException error) {
            throw new ResponseCustomError(error.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }
}