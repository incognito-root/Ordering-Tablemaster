package com.tablemasterordering.orderingtablemaster.models;

import java.util.ArrayList;

public class OrderModel {
    private long orderId;
    private String orderDescription;
    private String orderStatus;
    private String orderPlacedDate;
    private double orderAmount;
    private double orderExtraCharges;
    private long fkCustomerId;
    private ArrayList<OrderDetailModel> orderDetails;

    public OrderModel(String orderDescription, double orderAmount, double orderExtraCharges, long fkCustomerId, ArrayList<OrderDetailModel> orderDetails) {
        this.orderDescription = orderDescription;
        this.orderAmount = orderAmount;
        this.orderExtraCharges = orderExtraCharges;
        this.fkCustomerId = fkCustomerId;
        this.orderDetails = orderDetails;
    }

    public OrderModel() {
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getOrderExtraCharges() {
        return orderExtraCharges;
    }

    public void setOrderExtraCharges(double orderExtraCharges) {
        this.orderExtraCharges = orderExtraCharges;
    }

    public long getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(long fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }

    public ArrayList<OrderDetailModel> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailModel> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
