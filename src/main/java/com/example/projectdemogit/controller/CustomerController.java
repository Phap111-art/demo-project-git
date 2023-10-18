package com.example.projectdemogit.controller;

import com.example.projectdemogit.entity.Customer;
import com.example.projectdemogit.response.ResponseCustomData;
import com.example.projectdemogit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseCustomData> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseCustomData> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseCustomData> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseCustomData> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseCustomData> deleteCustomer(@PathVariable Long id) {
        ResponseCustomData response = customerService.deleteCustomer(id);
        return ResponseEntity.accepted().body(response);
    }
}