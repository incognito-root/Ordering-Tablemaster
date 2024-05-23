package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.CustomerService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.models.AddressModel;
import com.tablemasterordering.orderingtablemaster.models.SaveAddressRequest;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAddressController implements Initializable {

    @FXML
    private Button saveAddressButton;

    @FXML
    private Button skipAddressButton;

    @FXML
    private TextField area;

    @FXML
    private TextField streetAddress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.skipAddressButton.setVisible(false);
    }

    public void handleAddress(Event e) throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher("test");
        String source = ((Button) e.getSource()).getId();

        if (source.equals(saveAddressButton.getId())) {
            AddressModel a = new AddressModel(streetAddress.getText(), area.getText(), "Lahore");
            SaveAddressRequest addressRequest = new SaveAddressRequest(a.toString(), Auth.customerDetails.getCustomerId());
            CustomerService customerService = new CustomerService();
            customerService.saveCustomerAddress(addressRequest);
        }

        sceneSwitcher.switchScene("main-screen.fxml", saveAddressButton);
    }

    public Button getSaveAddressButton() {
        return saveAddressButton;
    }

    public void setSaveAddressButton(Button saveAddressButton) {
        this.saveAddressButton = saveAddressButton;
    }

    public Button getSkipAddressButton() {
        return skipAddressButton;
    }

    public void setSkipAddressButton(Button skipAddressButton) {
        this.skipAddressButton = skipAddressButton;
    }

    public TextField getArea() {
        return area;
    }

    public void setArea(TextField area) {
        this.area = area;
    }

    public TextField getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(TextField streetAddress) {
        this.streetAddress = streetAddress;
    }
}
