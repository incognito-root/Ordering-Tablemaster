package com.tablemasterordering.orderingtablemaster.models;

public class MenuItemModel {
    private String menuItemTitle;
    private double menuItemPrice;
    private String menuItemImageUrl;
    private String menuItemDescription;
    private String menuItemCategory;

    public MenuItemModel(String menuItemTitle, double menuItemPrice) {
        this.menuItemTitle = menuItemTitle;
        this.menuItemPrice = menuItemPrice;
    }

    public String getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(String menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
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
}
