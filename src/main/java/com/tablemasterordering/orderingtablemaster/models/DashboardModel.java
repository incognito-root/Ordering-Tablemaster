package com.tablemasterordering.orderingtablemaster.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashboardModel {
    private Integer totalOrders;
    private DiscountModel ongoingDiscount;
    private String address;
    private MenuItemModel mostOrderedMenuItem;
    private ArrayList<OrderModel> orders;

    public DashboardModel(Integer totalOrders, DiscountModel ongoingDiscount, String address, MenuItemModel mostOrderedMenuItem, ArrayList<OrderModel> orders) {
        this.totalOrders = totalOrders;
        this.ongoingDiscount = ongoingDiscount;
        this.address = address;
        this.mostOrderedMenuItem = mostOrderedMenuItem;
        this.orders = orders;
    }

    public DashboardModel() {
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public DiscountModel getOngoingDiscount() {
        return ongoingDiscount;
    }

    public void setOngoingDiscount(DiscountModel ongoingDiscount) {
        this.ongoingDiscount = ongoingDiscount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MenuItemModel getMostOrderedMenuItem() {
        return mostOrderedMenuItem;
    }

    public void setMostOrderedMenuItem(MenuItemModel mostOrderedMenuItem) {
        this.mostOrderedMenuItem = mostOrderedMenuItem;
    }

    public ArrayList<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderModel> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "DashboardModel{" +
                "totalOrders=" + totalOrders +
                ", ongoingDiscount=" + ongoingDiscount.getDiscountTitle() +
                ", address='" + address + '\'' +
                ", mostOrderedMenuItem=" + mostOrderedMenuItem.getMenuItemName() +
                ", orders=" + orders.getFirst().getOrderAmount() +
                '}';
    }
}
