package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.OrderService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.helper_functions.Popup;
import com.tablemasterordering.orderingtablemaster.helper_functions.PopupTypeEnum;
import com.tablemasterordering.orderingtablemaster.models.CartMenuItemModel;
import com.tablemasterordering.orderingtablemaster.models.OrderDetailModel;
import com.tablemasterordering.orderingtablemaster.models.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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

    @FXML
    private TextField orderNotes;

    @FXML
    private Label cartAddress;

    private static ObservableList<CartMenuItemModel> cartItemsList = FXCollections.observableArrayList();

    private static double totalBill = 0;

    private static double billTax = 0;

    private static double finalBill = 0;

    private static double cartDiscountValue = 0;

    public static final String emptyCartMessage = "Cart Is Empty. Please add some items to cart first";

    public static final String emptyCartTitle = "Empty Cart";

    public static final String noAddressMessage = "Cannot Place an Order without Address. Set Address in Settings First Please";

    public static final String noAddressTitle = "Address Missing";

    public CartController() {
    }

    public void closeCart() {
        this.mainCartArea.setVisible(false);

        Color color = new Color(0, 0, 0, 0);
        BackgroundFill fill = new BackgroundFill(color, null, null);
        Background background = new Background(fill);
        mainCartArea.setBackground(background);
    }

    private void emptyCart() {
        this.cartItemsList.clear();
        this.cartItems.getChildren().clear();
    }

    public void createOrder() {

        if (cartItemsList.isEmpty()) {
            Popup.showPopup(PopupTypeEnum.ERROR, emptyCartMessage, emptyCartTitle);
            closeCart();
            return;
        }

        if (cartAddress.getText().equals("Save In Settings")) {
            Popup.showPopup(PopupTypeEnum.ERROR, noAddressMessage, noAddressTitle);
            closeCart();
            return;
        }

        OrderModel orderModel = new OrderModel();
        orderModel.setOrderDescription(orderNotes.getText() == null ? " " : orderNotes.getText());
        System.out.println(orderNotes.getText());
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
                emptyCart();
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

        if (!Auth.customerDetails.getAddress().isEmpty()) {
            cartAddress.setText(Auth.customerDetails.getAddress());
        }
    }

    private void updateCartView() {
        cartItems.getChildren().clear();
        totalBill = 0;

        for (CartMenuItemModel item : cartItemsList) {
            try {
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
        cartDiscountValue = (Auth.discount / 100) * totalBill;
        finalBill = (totalBill + billTax) - cartDiscountValue;

        DecimalFormat df = new DecimalFormat("#.00");

        cartTax.setText(df.format(billTax));
        cartTotal.setText(df.format(totalBill));
        cartSubTotal.setText(df.format(finalBill));
        cartDiscount.setText(df.format(cartDiscountValue));
    }

    public void addMenuItemToCart(CartMenuItemModel cartMenuItemToAdd) {
        if (!increaseQuantity(cartMenuItemToAdd)) {
            cartMenuItemToAdd.setQuantity(1);
            cartItemsList.add(cartMenuItemToAdd);
        }
    }

    public boolean increaseQuantity(CartMenuItemModel cartMenuItemToAdd) {
        for (CartMenuItemModel m : cartItemsList) {
            if (m.getMenuItemName().equals(cartMenuItemToAdd.getMenuItemName())) {
                m.increaseQuantity();
                cartItemsList.set(cartItemsList.indexOf(m), m);
                return true;
            }
        }

        return false;
    }

    public void quantityChange(String cartMenuItemToAdd, String changeType) {
        CartMenuItemModel itemToUpdate = null;

        for (CartMenuItemModel m : cartItemsList) {
            if (m.getMenuItemName().equals(cartMenuItemToAdd)) {
                itemToUpdate = m;
                break;
            }
        }

        if (itemToUpdate != null) {
            if (changeType.equals("increase")) {
                itemToUpdate.increaseQuantity();
            } else if (changeType.equals("reduce")) {
                if (!itemToUpdate.decreaseQuantity()) {
                    cartItemsList.remove(itemToUpdate);
                }
            }

            if (!cartItemsList.isEmpty() && itemToUpdate.getQuantity() > 0) {
                int index = cartItemsList.indexOf(itemToUpdate);
                if (index >= 0) {
                    cartItemsList.set(index, itemToUpdate);
                }
            }
        }
    }

    public void removeMenuItemFromCart(String cartMenuItemToRemove) {
        CartMenuItemModel itemToRemove = new CartMenuItemModel();

        for (CartMenuItemModel m : cartItemsList) {
            if (m.getMenuItemName().equals(cartMenuItemToRemove)) {
                itemToRemove = m;
            }
        }

        cartItemsList.remove(itemToRemove);
    }


    public static ObservableList<CartMenuItemModel> getCartItemsList() {
        return cartItemsList;
    }
}
