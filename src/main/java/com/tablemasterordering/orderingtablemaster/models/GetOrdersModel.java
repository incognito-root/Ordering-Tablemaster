package com.tablemasterordering.orderingtablemaster.models;

import java.time.LocalDate;

public class GetOrdersModel {
    private long orderId;
    private String orderDescription;
    private String orderStatus;
    private String orderPlacedDate;
    private String orderAmount;
    private double orderExtraCharges;
    private long fkCustomerId;

    public GetOrdersModel(long orderId, String orderDescription, String orderStatus, LocalDate orderPlacedDate, double orderAmount, double orderExtraCharges, long fkCustomerId) {
        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.orderStatus = orderStatus;
        this.orderPlacedDate = orderPlacedDate.toString();
        this.orderAmount = String.valueOf(orderAmount);
        this.orderExtraCharges = orderExtraCharges;
        this.fkCustomerId = fkCustomerId;
    }

    public GetOrdersModel() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
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

    public void setOrderPlacedDate(LocalDate orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate.toString();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = String.valueOf(orderAmount);
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
}
