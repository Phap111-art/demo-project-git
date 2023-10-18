package com.example.projectdemogit.api;

import com.example.projectdemogit.entity.Customer;
import com.example.projectdemogit.response.ResponseObject;
import com.example.projectdemogit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ControllerView {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public String ádasd(Model model) {
        model.addAttribute("getAllCustomer" , customerService.getAllCustomers());
        return "user";
    }
}
