package com.example.projectdemogit.service.impl;

import com.example.projectdemogit.dtos.customerDTO.CreateCustomerDTO;
import com.example.projectdemogit.dtos.customerDTO.UpdateCustomerDTO;
import com.example.projectdemogit.entity.Customer;
import com.example.projectdemogit.mapper.CustomMapper;
import com.example.projectdemogit.repository.CustomerRepository;
import com.example.projectdemogit.response.CustomResponse;
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
    public CustomResponse getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        CustomResponse responseObject = new CustomResponse("get all customer successfully !", HttpStatus.OK.value(), customers);
        return responseObject;
    }

    @Override
    public CustomResponse getCustomerById(Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                return new CustomResponse("Found Id " + id + " successfully", HttpStatus.OK.value(), customer.get());
            } else {
                throw new IllegalArgumentException("Not found id customer: " + id);
            }
        } catch (RuntimeException e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(),null);
        }
    }

    @Override
    public CustomResponse createCustomer(CreateCustomerDTO dto) {
        try {
            Customer customerEntity = CustomMapper.toEntity(dto, Customer.class);
            Customer savedCustomer = customerRepository.save(customerEntity);

            return new CustomResponse("Save Successfully", HttpStatus.CREATED.value(),
                    CustomMapper.toDTO(savedCustomer, CreateCustomerDTO.class));
        } catch (RuntimeException e) {
            return new CustomResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),new CreateCustomerDTO());
        }
    }

    @Override
    public CustomResponse updateCustomer(Long id, UpdateCustomerDTO dto) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(id);
            if (existingCustomer.isPresent()){
                Customer updatedCustomerEntity = CustomMapper.toEntity(dto, Customer.class);
                updatedCustomerEntity.setCustomerId(id);
                return new CustomResponse("Customer updated successfully!", HttpStatus.OK.value(),
                        CustomMapper.toDTO(customerRepository.save(updatedCustomerEntity), UpdateCustomerDTO.class));
            }else{
                throw new IllegalArgumentException("Update failed! Customer not found: " + id);
            }
        } catch (IllegalArgumentException e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(),new UpdateCustomerDTO());
        }
    }

    @Override
    public CustomResponse deleteCustomer(Long id) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(id);
            if (existingCustomer.isPresent()) {
                customerRepository.deleteById(id);
                return new CustomResponse("Customer deleted successfully!", HttpStatus.ACCEPTED.value(), "");
            } else {
                throw new IllegalArgumentException("Customer not found: " + id);
            }
        } catch (RuntimeException e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        }
    }
}