package com.tablemasterordering.orderingtablemaster.models;

import java.time.LocalDateTime;

public class MenuItemModel {
    private long menuItemId;
    private String menuItemName;
    private double menuItemPrice;
    private String menuItemImageUrl;
    private String menuItemDescription;
    private int fkCategoryId;
    private int menuItemServing;
    private boolean menuItemIsAvailable;
    private int menuItemTotalOrders;
    private LocalDateTime menuItemAddedDate;
    private String menuItemImage;

    public MenuItemModel(long menuItemId, String menuItemName, double menuItemPrice, String menuItemImageUrl, String menuItemDescription, int fkCategoryId, int menuItemServing, boolean menuItemIsAvailable, int menuItemTotalOrders, LocalDateTime menuItemAddedDate, String menuItemImage) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.menuItemImageUrl = menuItemImageUrl;
        this.menuItemDescription = menuItemDescription;
        this.fkCategoryId = fkCategoryId;
        this.menuItemServing = menuItemServing;
        this.menuItemIsAvailable = menuItemIsAvailable;
        this.menuItemTotalOrders = menuItemTotalOrders;
        this.menuItemAddedDate = menuItemAddedDate;
        this.menuItemImage = menuItemImage;
    }

    public MenuItemModel() {
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public double getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public String getMenuItemImageUrl() {
        return menuItemImageUrl;
    }

    public void setMenuItemImageUrl(String menuItemImageUrl) {
        this.menuItemImageUrl = menuItemImageUrl;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public int getFkCategoryId() {
        return fkCategoryId;
    }

    public void setFkCategoryId(int fkCategoryId) {
        this.fkCategoryId = fkCategoryId;
    }

    public int getMenuItemServing() {
        return menuItemServing;
    }

    public void setMenuItemServing(int menuItemServing) {
        this.menuItemServing = menuItemServing;
    }

    public boolean isMenuItemIsAvailable() {
        return menuItemIsAvailable;
    }

    public void setMenuItemIsAvailable(boolean menuItemIsAvailable) {
        this.menuItemIsAvailable = menuItemIsAvailable;
    }

    public int getMenuItemTotalOrders() {
        return menuItemTotalOrders;
    }

    public void setMenuItemTotalOrders(int menuItemTotalOrders) {
        this.menuItemTotalOrders = menuItemTotalOrders;
    }

    public LocalDateTime getMenuItemAddedDate() {
        return menuItemAddedDate;
    }

    public void setMenuItemAddedDate(LocalDateTime menuItemAddedDate) {
        this.menuItemAddedDate = menuItemAddedDate;
    }

    public String getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(String menuItemImage) {
        this.menuItemImage = menuItemImage;
    }
}
