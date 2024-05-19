package com.tablemasterordering.orderingtablemaster.models;

public class AddressModel {

    private String streetAddress;
    private String area;
    private String city;

    public AddressModel(String streetAddress, String area, String city) {
        this.streetAddress = streetAddress;
        this.area = area;
        this.city = city;
    }

    public AddressModel() {
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return streetAddress + ", " + area + ", " + city;
    }
}