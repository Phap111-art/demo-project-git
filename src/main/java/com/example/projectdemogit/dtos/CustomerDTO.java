package com.example.projectdemogit.dtos;
import javax.validation.constraints.*;

public class CustomerDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must have 10 digits")
    private String phone;

    private AddressDTO address;
}
