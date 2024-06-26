package com.desafio.votacao.api;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

public class ApiError {

    private HttpStatus status;
    private String message;
    private String errorDetail;

    public ApiError(HttpStatus status, String message, String errorDetail) {
        this.status = status;
        this.message = message;
        this.errorDetail = errorDetail;
    }

    public static ApiError of(HttpStatus status, String message, String errorDetail) {
        return new ApiError(status, message, errorDetail);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
