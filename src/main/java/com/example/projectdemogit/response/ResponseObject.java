package com.example.projectdemogit.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    private String mess;
    private int statusCode;
    private Object data;
}
