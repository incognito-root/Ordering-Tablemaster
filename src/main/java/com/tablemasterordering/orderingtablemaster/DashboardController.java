package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.CustomerService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.models.DashboardModel;
import com.tablemasterordering.orderingtablemaster.models.GetCustomerById;
import com.tablemasterordering.orderingtablemaster.models.GetOrdersModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label addressLabel;

    @FXML
    private Text discountsLabel;

    @FXML
    private Label menuItemDescription;

    @FXML
    private Label menuItemPrice;

    @FXML
    private Label menuItemTitle;

    @FXML
    private Text totalOrdersLabel;

    @FXML
    private TableView<GetOrdersModel> allOrders;

    @FXML
    private TableColumn<?, ?> tableOrderDate;

    @FXML
    private TableColumn<?, ?> tableOrderNotes;

    @FXML
    private TableColumn<?, ?> tableOrderPayment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DashboardModel dashboardModel = new CustomerService().getCustomerDashboardData(new GetCustomerById(Auth.customerDetails.getCustomerId()));

            if (dashboardModel.getTotalOrders() > 0) {
                totalOrdersLabel.setText(dashboardModel.getTotalOrders() + " Orders");
            }

            if (dashboardModel.getOngoingDiscount().getDiscountAmount() > 0) {
                discountsLabel.setText(dashboardModel.getOngoingDiscount().getDiscountTitle() + "  " + dashboardModel.getOngoingDiscount().getDiscountAmount() + " % Off");
            }

            if (!dashboardModel.getAddress().isEmpty()) {
                addressLabel.setText(dashboardModel.getAddress());
            }

            if (dashboardModel.getMostOrderedMenuItem() != null) {
                menuItemTitle.setText(dashboardModel.getMostOrderedMenuItem().getMenuItemName());
                menuItemDescription.setText(dashboardModel.getMostOrderedMenuItem().getMenuItemDescription());
                menuItemDescription.setVisible(true);
                menuItemPrice.setText(String.format("%.2f", dashboardModel.getMostOrderedMenuItem().getMenuItemPrice()));
                menuItemPrice.setVisible(true);
            }

            // Bind data to table columns
            tableOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderPlacedDate"));
            tableOrderNotes.setCellValueFactory(new PropertyValueFactory<>("orderDescription"));
            tableOrderPayment.setCellValueFactory(new PropertyValueFactory<>("orderAmount"));

            // Add orders to the table
            ObservableList<GetOrdersModel> ordersModels = FXCollections.observableArrayList(dashboardModel.getOrders());
            allOrders.setItems(ordersModels);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
