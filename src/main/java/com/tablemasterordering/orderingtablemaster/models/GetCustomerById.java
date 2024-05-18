package com.tablemasterordering.orderingtablemaster.models;

public class GetCustomerById {
    private long customerId;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public GetCustomerById(long customerId) {
        this.customerId = customerId;
    }

    public GetCustomerById() {
    }
}