<<<<<<<< HEAD:src/main/java/com/example/projectdemogit/dtos/request/address/AddressDto.java
package com.example.projectdemogit.dtos.request.address;

========
package com.example.projectdemogit.dtos.request.customer;
>>>>>>>> origin/main:src/main/java/com/example/projectdemogit/dtos/request/customer/AddressDTO.java

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zipcode is required")
    private String zipcode;
}
