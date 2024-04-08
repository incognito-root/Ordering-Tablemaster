package com.tablemasterordering.orderingtablemaster;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CartMenuItemController {
    @FXML
    private ImageView menuItemDeleteButton;

    @FXML
    private ImageView menuItemImage;

    @FXML
    private Text menuItemPrice;

    @FXML
    private Label menuItemQuantity;

    @FXML
    private Text menuItemTitle;

    @FXML
    private Text menuItemTotalPrice;

    public void setData(String menuItemTitle, String menuItemPrice, String menuItemQuantity, String menuItemTotalPrice) {
        this.menuItemTitle.setText(menuItemTitle);
        this.menuItemPrice.setText(menuItemPrice);
        this.menuItemQuantity.setText(menuItemQuantity);
        this.menuItemTotalPrice.setText(menuItemTotalPrice);
    }

    public CartMenuItemController() {
    }

    public ImageView getMenuItemDeleteButton() {
        return menuItemDeleteButton;
    }

    public void setMenuItemDeleteButton(ImageView menuItemDeleteButton) {
        this.menuItemDeleteButton = menuItemDeleteButton;
    }

    public ImageView getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(ImageView menuItemImage) {
        this.menuItemImage = menuItemImage;
    }

    public Text getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(Text menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public Label getMenuItemQuantity() {
        return menuItemQuantity;
    }

    public void setMenuItemQuantity(Label menuItemQuantity) {
        this.menuItemQuantity = menuItemQuantity;
    }

    public Text getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(Text menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
    }

    public Text getMenuItemTotalPrice() {
        return menuItemTotalPrice;
    }

    public void setMenuItemTotalPrice(Text menuItemTotalPrice) {
        this.menuItemTotalPrice = menuItemTotalPrice;
    }
}
