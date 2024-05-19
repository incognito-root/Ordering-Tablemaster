package com.tablemasterordering.orderingtablemaster;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddressAfterSignUpController implements Initializable {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ImageView logoImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-address.fxml"));
        try {
            AnchorPane scene = loader.load();
            mainBorderPane.setCenter(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
