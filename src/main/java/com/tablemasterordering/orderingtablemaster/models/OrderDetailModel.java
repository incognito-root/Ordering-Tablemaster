package com.tablemasterordering.orderingtablemaster.models;

public class OrderDetailModel {
    private int orderDetailQuantity;
    private long fkMenuItemId;

    public OrderDetailModel(int orderDetailQuantity, long fkMenuItemId) {
        this.orderDetailQuantity = orderDetailQuantity;
        this.fkMenuItemId = fkMenuItemId;
    }

    public OrderDetailModel() {
    }

    public int getOrderDetailQuantity() {
        return orderDetailQuantity;
    }

    public void setOrderDetailQuantity(int orderDetailQuantity) {
        this.orderDetailQuantity = orderDetailQuantity;
    }

    public long getFkMenuItemId() {
        return fkMenuItemId;
    }

    public void setFkMenuItemId(long fkMenuItemId) {
        this.fkMenuItemId = fkMenuItemId;
    }
}
