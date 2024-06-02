package com.tablemasterordering.orderingtablemaster.models;

import java.time.LocalDate;

public class CustomerModel {
    private long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;
    private String notes;
    private String gender;
    private String dateOfBirth;
    private Long fkMenuItemMostOrdered;
    private boolean membership;
    private boolean isVerified;

    public CustomerModel(String firstName, String lastName, String phoneNumber, String address, String email, String password, String notes, String gender, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.notes = notes;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public CustomerModel(long customerId, String firstName, String lastName, String phoneNumber, String address, String email, String password, String notes, String gender, String dateOfBirth, Long fkMenuItemMostOrdered, boolean membership, boolean isVerified) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.notes = notes;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
        this.membership = membership;
        this.isVerified = isVerified;
    }

    public CustomerModel() {
    }

    public CustomerModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getFkMenuItemMostOrdered() {
        return fkMenuItemMostOrdered;
    }

    public void setFkMenuItemMostOrdered(Long fkMenuItemMostOrdered) {
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
    }

    public boolean getIsMembership() {
        return membership;
    }

    public void setIsMembership(boolean membership) {
        this.membership = membership;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return customerId + " " + firstName;
    }
}
