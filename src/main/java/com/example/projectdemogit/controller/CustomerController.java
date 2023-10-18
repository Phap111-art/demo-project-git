package com.example.projectdemogit.controller;

import com.example.projectdemogit.entity.Customer;
import com.example.projectdemogit.response.ResponseObject;
import com.example.projectdemogit.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();

        ResponseObject responseObject = new ResponseObject("get all customer successfully !", HttpStatus.OK.value(), customers);
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseObject> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        ResponseObject responseObject;
        if (customer.isPresent()) {
            responseObject = new ResponseObject("Found Id " + id + "successfully ", HttpStatus.OK.value(), customer.get());
            return ResponseEntity.ok(responseObject);
        } else {
            responseObject = new ResponseObject("not found id customer !!" + id, HttpStatus.NOT_FOUND.value(), "");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        ResponseObject responseObject = new ResponseObject("Save Successfully", 201, createdCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer);
        ResponseObject responseObject;
        if (Objects.nonNull(customer)) {
            responseObject = new ResponseObject("Customer updated successfully!", HttpStatus.OK.value(), customer);
        } else {
            responseObject = new ResponseObject("Update failed! Customer not found", HttpStatus.NOT_FOUND.value(), "");
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable Long id) {
        boolean isDeleted = customerService.deleteCustomer(id);

        if (isDeleted) {
            ResponseObject responseObject = new ResponseObject("Customer deleted successfully!", HttpStatus.ACCEPTED.value(), "");
            return ResponseEntity.accepted().body(responseObject);
        } else {
            ResponseObject responseObject = new ResponseObject("Customer not found", HttpStatus.NOT_FOUND.value(), "");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }
}