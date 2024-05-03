package com.tablemasterordering.orderingtablemaster.models;

import java.time.LocalDateTime;

public class CartMenuItemModel extends MenuItemModel {
    private int quantity;

    public CartMenuItemModel(MenuItemModel menuItemModel) {
        super(menuItemModel.getMenuItemName(), menuItemModel.getMenuItemPrice(), menuItemModel.getMenuItemImageUrl(), menuItemModel.getMenuItemDescription(),
                menuItemModel.getFkCategoryId(), menuItemModel.getMenuItemServing(), menuItemModel.isMenuItemIsAvailable(), menuItemModel.getMenuItemTotalOrders(),
                menuItemModel.getMenuItemAddedDate(), menuItemModel.getMenuItemImage());
        this.setQuantity(1);
    }

    public CartMenuItemModel() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }
}
