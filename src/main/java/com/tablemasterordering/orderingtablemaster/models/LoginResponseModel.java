package com.tablemasterordering.orderingtablemaster.models;

public class LoginResponseModel {
    private long id;
    private String message;

    public LoginResponseModel(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public LoginResponseModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
