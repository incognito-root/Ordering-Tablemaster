package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.OrderService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.models.CartMenuItemModel;
import com.tablemasterordering.orderingtablemaster.models.OrderDetailModel;
import com.tablemasterordering.orderingtablemaster.models.OrderModel;
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
import java.util.ArrayList;
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

    public void createOrder() {
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderDescription(" ");
        orderModel.setOrderAmount(finalBill);
        orderModel.setFkCustomerId(Auth.customerId != 0 ? Auth.customerId : Auth.customerDetails.getCustomerId());
        orderModel.setOrderExtraCharges(0);

        ArrayList<OrderDetailModel> orderDetail = new ArrayList<>();

        for (CartMenuItemModel cartMenuItemModel : cartItemsList) {
            OrderDetailModel orderDetailModel = new OrderDetailModel(cartMenuItemModel.getQuantity(), cartMenuItemModel.getMenuItemId());
            orderDetail.add(orderDetailModel);
        }

        orderModel.setOrderDetails(orderDetail);

        OrderService orderService = new OrderService();
        try {
            boolean orderCreated = orderService.createOrder(orderModel);

            if (orderCreated) {
                closeCart();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cartItemsList.addListener((ListChangeListener<CartMenuItemModel>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateCartView();
                }
            }
        });

        updateCartView();

    }

    private void updateCartView() {
        cartItems.getChildren().clear();
        totalBill = 0;

        for (CartMenuItemModel item : cartItemsList) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-menu-item-card.fxml"));
                AnchorPane anchorPane = loader.load();
                CartMenuItemController paneController = loader.getController();
                paneController.setData(item.getMenuItemName(), String.valueOf(item.getMenuItemPrice()), String.valueOf(item.getQuantity()), String.valueOf(item.getMenuItemPrice()));
                cartItems.getChildren().add(anchorPane);

                totalBill += item.getMenuItemPrice() * item.getQuantity();

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

    public void addMenuItemToCart(CartMenuItemModel cartMenuItemToAdd) {

        for (CartMenuItemModel m : cartItemsList) {
            if (m.getMenuItemName().equals(cartMenuItemToAdd.getMenuItemName())) {
                m.increaseQuantity();
                cartItemsList.set(cartItemsList.indexOf(m), m);
                return;
            }
        }

        cartItemsList.add(cartMenuItemToAdd);
    }

    public void removeMenuItemFromCart(String cartMenuItemToRemove) {
        for (CartMenuItemModel m : cartItemsList) {
            if (m.getMenuItemName().equals(cartMenuItemToRemove)) {
                System.out.println(m.getMenuItemName());
                System.out.println(cartMenuItemToRemove);
                cartItemsList.remove(m);
            }
        }
    }

    public ObservableList<CartMenuItemModel> getCartItemsList() {
        return cartItemsList;
    }
}
