package com.example.projectdemogit.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCustomData {
    private String mess;
    private int httpStatus;
    private Object data;
}
