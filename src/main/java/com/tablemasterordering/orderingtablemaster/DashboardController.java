package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.CustomerService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Text totalOrdersLabel;

    @FXML
    private TableView<OrderModel> allOrdersTable;

    @FXML
    private TableColumn<?, ?> tableOrderDate;

    @FXML
    private TableColumn<?, ?> tableOrderNotes;

    @FXML
    private TableColumn<OrderModel, ?> tableOrderPayment;

    @FXML
    VBox usualOrderBox;

    @FXML
    private Label usualOrderTotal;

    @FXML
    private TextField orderSearchField;

    @FXML
    private ImageView ascendingSort;

    @FXML
    private ImageView descendingSort;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField orderDateField;

    private static ObservableList<OrderModel> ordersModels;

    private static ObservableList<OrderModel> ordersBackup;

    private String query;

    private boolean orderDateSearch = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DashboardModel dashboardModel = new CustomerService().getCustomerDashboardData(Auth.customerDetails.getCustomerId());

            if (dashboardModel.getTotalOrders() > 0) {
                totalOrdersLabel.setText(dashboardModel.getTotalOrders() + " Orders");
            }

            tableOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderPlacedDate"));
            tableOrderNotes.setCellValueFactory(new PropertyValueFactory<>("orderDescription"));
            tableOrderPayment.setCellValueFactory(new PropertyValueFactory<>("orderAmount"));

            ordersModels = FXCollections.observableArrayList(dashboardModel.getOrders());
            allOrdersTable.setItems(ordersModels);
            ordersBackup = ordersModels;

            populateMostFrequentOrder();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateMostFrequentOrder() {
        try {
            OrderModel orderModel = new CustomerService().getMostFrequentOrder();
            ArrayList<OrderDetailModel> orderDetails = orderModel.getOrderDetails();
            ArrayList<CartMenuItemModel> menuItems = new ArrayList<>();
            double orderAmount = 0;

            for (OrderDetailModel orderDetail : orderDetails) {
                for (MenuItemModel menuItem : HomeController.getAllMenuItems()) {
                    if (orderDetail.getFkMenuItemId() == menuItem.getMenuItemId()) {
                        CartMenuItemModel cartItem = new CartMenuItemModel(menuItem);
                        cartItem.setQuantity(orderDetail.getOrderDetailQuantity());
                        menuItems.add(cartItem);
                        orderAmount += orderDetail.getOrderDetailQuantity() * menuItem.getMenuItemPrice();
                    }
                }
            }


            for (CartMenuItemModel item : menuItems) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-menu-item-card.fxml"));
                AnchorPane anchorPane = loader.load();
                CartMenuItemController paneController = loader.getController();
                paneController.hideQuantityButtons();
                paneController.setData(item.getMenuItemName(), String.valueOf(item.getQuantity()), String.valueOf(item.getMenuItemPrice() * item.getQuantity()), String.valueOf(item.getMenuItemPrice()));
                usualOrderBox.getChildren().add(anchorPane);
            }

            usualOrderTotal.setText(String.valueOf(orderAmount));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOrders() {

        if (query == null || query.isEmpty()) {
            allOrdersTable.setItems(ordersBackup);
            return;
        }

        ArrayList<OrderModel> filteredOrders = new ArrayList<>();

        if (orderDateSearch) {
            for (OrderModel order : ordersBackup) {
                if (order.getOrderPlacedDate().equals(query)
                ) {
                    filteredOrders.add(order);
                }
            }

            orderDateSearch = false;
        } else {
            query = orderSearchField.getText().toLowerCase();

            for (OrderModel order : ordersModels) {
                if (order.getOrderPlacedDate().toLowerCase().contains(query) ||
                        order.getOrderDescription().toLowerCase().contains(query) ||
                        String.valueOf(order.getOrderAmount()).toLowerCase().contains(query)
                ) {
                    filteredOrders.add(order);
                }
            }
        }

        allOrdersTable.setItems(FXCollections.observableArrayList(filteredOrders));
    }

    public void sortOrderByAmount(Event e) {
        String source = ((ImageView) e.getSource()).getId();

        if (source.equals("ascendingSort")) {
            tableOrderPayment.setSortType(TableColumn.SortType.DESCENDING);
            ascendingSort.setVisible(false);
            descendingSort.setVisible(true);
        }

        if (source.equals("descendingSort")) {
            tableOrderPayment.setSortType(TableColumn.SortType.ASCENDING);
            descendingSort.setVisible(false);
            ascendingSort.setVisible(true);
        }

        allOrdersTable.getSortOrder().clear();
        allOrdersTable.getSortOrder().add(tableOrderPayment);
        allOrdersTable.sort();
    }

    public void getOrderByDate() {
        String dateToSearchFor = datePicker.getValue().toString();
        orderDateField.setText(dateToSearchFor);

        query = dateToSearchFor;

        orderDateSearch = true;

        searchOrders();
    }
}
