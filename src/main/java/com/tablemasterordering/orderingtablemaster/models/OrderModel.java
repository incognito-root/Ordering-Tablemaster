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

    public OrderModel(long orderId, String orderDescription, String orderStatus, String orderPlacedDate, double orderAmount, double orderExtraCharges, long fkCustomerId, ArrayList<OrderDetailModel> orderDetails) {
        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.orderStatus = orderStatus;
        this.orderPlacedDate = orderPlacedDate;
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public void setOrderPlacedDate(String orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    public void setOrderDetails(ArrayList<OrderDetailModel> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
