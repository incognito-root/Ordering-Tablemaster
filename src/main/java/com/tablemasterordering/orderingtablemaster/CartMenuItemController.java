package com.tablemasterordering.orderingtablemaster;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CartMenuItemController {
    @FXML
    private Label menuItemQuantity;

    @FXML
    private Label menuItemTitle;

    @FXML
    private Text menuItemTotalPrice;

    @FXML
    private Text menuItemUnitPrice;

    @FXML
    private Label increaseQuantityButton;

    @FXML
    private Label reduceQuantityButton;

    public void setData(String menuItemTitle, String menuItemQuantity, String menuItemTotalPrice, String menuItemUnitPrice) {
        this.menuItemTitle.setText(menuItemTitle);
        this.menuItemUnitPrice.setText(menuItemUnitPrice);
        this.menuItemQuantity.setText(menuItemQuantity);
        this.menuItemTotalPrice.setText(menuItemTotalPrice);
    }

    public CartMenuItemController() {
    }

    public Label getMenuItemQuantity() {
        return menuItemQuantity;
    }

    public void setMenuItemQuantity(Label menuItemQuantity) {
        this.menuItemQuantity = menuItemQuantity;
    }

    public Label getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(Label menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
    }

    public Text getMenuItemTotalPrice() {
        return menuItemTotalPrice;
    }

    public void setMenuItemTotalPrice(Text menuItemTotalPrice) {
        this.menuItemTotalPrice = menuItemTotalPrice;
    }

    public void changeQuantity(Event e) {
        String source = ((Label) e.getSource()).getId();
        String type = "increase";
        CartController cartController = new CartController();

        if (source.equals("increaseQuantityButton")) {
            type = "increase";
        } else if (source.equals("reduceQuantityButton")) {
            type = "reduce";
        }

        cartController.quantityChange(menuItemTitle.getText(), type);
    }

    public void hideQuantityButtons() {
        reduceQuantityButton.setVisible(false);
        increaseQuantityButton.setVisible(false);
    }
}
