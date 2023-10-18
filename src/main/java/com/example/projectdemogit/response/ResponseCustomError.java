package com.example.projectdemogit.response;

public class ResponseCustomError extends RuntimeException {
    private int httpStatus;

    public ResponseCustomError(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
