package com.tablemasterordering.orderingtablemaster.models;

public class SaveAddressRequest {
    private String address;
    private long customerId;

    public SaveAddressRequest(String address, long customerId) {
        this.address = address;
        this.customerId = customerId;
    }

    public SaveAddressRequest() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
