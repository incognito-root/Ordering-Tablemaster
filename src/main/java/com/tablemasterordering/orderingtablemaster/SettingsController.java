package com.tablemasterordering.orderingtablemaster;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-address.fxml"));
        try {
            AnchorPane scene = loader.load();
            CustomerAddressController customerAddressController = loader.getController();
            customerAddressController.getSkipAddressButton().setVisible(false);
            mainBorderPane.setCenter(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
