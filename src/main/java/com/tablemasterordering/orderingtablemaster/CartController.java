package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.CartMenuItemModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    private Text cartDiscount;

    @FXML
    private VBox cartItems;

    @FXML
    private Text cartSubTotal;

    @FXML
    private Text cartTax;

    @FXML
    private Text cartTotal;

    @FXML
    private AnchorPane mainCartArea;

    private static ObservableList<CartMenuItemModel> cartItemsList = FXCollections.observableArrayList();

    private static double totalBill = 0;

    private static double billTax = 0;

    private static double finalBill = 0;

    public CartController() {
    }

    public void closeCart() {
        this.mainCartArea.setVisible(false);

        Color color = new Color(0, 0, 0, 0);
        BackgroundFill fill = new BackgroundFill(color, null, null);
        Background background = new Background(fill);
        mainCartArea.setBackground(background);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cartItemsList.addListener((ListChangeListener<CartMenuItemModel>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {

                    for (CartMenuItemModel addedItem : cartItemsList) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-menu-item-card.fxml"));
                            AnchorPane anchorPane = loader.load();
                            CartMenuItemController paneController = loader.getController();
                            paneController.setData(addedItem.getMenuItemName(), String.valueOf(addedItem.getMenuItemPrice()), String.valueOf(addedItem.getQuantity()), String.valueOf(addedItem.getMenuItemPrice()));
                            cartItems.getChildren().add(anchorPane);

                            totalBill += addedItem.getMenuItemPrice();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    billTax = 0.16 * totalBill;
                    finalBill = totalBill + billTax;

                    cartTax.setText(String.valueOf(billTax));
                    cartTotal.setText(String.valueOf(totalBill));
                    cartSubTotal.setText(String.valueOf(finalBill));
                    cartDiscount.setText(String.valueOf(0));
                }

                if (c.wasRemoved()) {
                    cartItems.getChildren().clear();
                }
            }
        });

    }



    public void addMenuItemToCart(CartMenuItemModel cartMenuItemToAdd) {

        for (CartMenuItemModel m : cartItemsList) {
            if (m.getMenuItemName().equals(cartMenuItemToAdd.getMenuItemName())) {
                cartItemsList.remove(m);
                m.increaseQuantity();
                cartItemsList.add(m);
                return;
            }
        }

        cartItemsList.add(cartMenuItemToAdd);
    }

    public ObservableList<CartMenuItemModel> getCartItemsList() {
        return cartItemsList;
    }
}
