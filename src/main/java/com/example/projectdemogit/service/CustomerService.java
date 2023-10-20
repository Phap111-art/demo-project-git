package com.example.projectdemogit.service;

import com.example.projectdemogit.dtos.customerDTO.CreateCustomerDTO;
import com.example.projectdemogit.dtos.customerDTO.UpdateCustomerDTO;
import com.example.projectdemogit.response.CustomResponse;

public interface CustomerService {
    CustomResponse getAllCustomers();
    CustomResponse getCustomerById(Long id);
    CustomResponse createCustomer(CreateCustomerDTO customerDTO);
    CustomResponse updateCustomer(Long id, UpdateCustomerDTO updatedCustomerDTO);
    CustomResponse deleteCustomer(Long id);
}