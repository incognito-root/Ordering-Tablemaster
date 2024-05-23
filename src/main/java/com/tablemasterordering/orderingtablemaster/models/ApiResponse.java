package com.tablemasterordering.orderingtablemaster.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    @JsonIgnore
    private Throwable error;

    public ApiResponse(boolean success, String message, T data, Throwable error) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public ApiResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
