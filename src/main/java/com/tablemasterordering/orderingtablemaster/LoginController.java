package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.CustomerService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.models.GetCustomerById;
import com.tablemasterordering.orderingtablemaster.models.LoginModel;
import com.tablemasterordering.orderingtablemaster.models.LoginResponseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox stayLoggedIn;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    private Stage stage;

    public void onLogin(ActionEvent event) throws NullPointerException, IOException {
        String email = emailField.getText();
        String pass = passwordField.getText();

        LoginModel customer = new LoginModel(email, pass);

        this.stage = (Stage) emailField.getScene().getWindow();

        CustomerService customerService = new CustomerService();
        LoginResponseModel loginResponseModel = customerService.customerLogin(customer);

        if (loginResponseModel.getId() != 0) {

            Auth.setCustomerDetails(loginResponseModel.getId());

            if (stayLoggedIn.isSelected()) {
                saveCookie(loginResponseModel.getId());
            }

            SceneSwitcher sceneSwitcher = new SceneSwitcher("test");
            sceneSwitcher.switchScene("main-screen.fxml", emailField);

        } else {
            errorLabel.setVisible(true);
            loginButton.setText("Retry");
            emailField.setStyle("-fx-border-color: #EA6969; -fx-border-width: 2px; -fx-border-radius: 15px");
            passwordField.setStyle("-fx-border-color: #EA6969; -fx-border-width: 2px; -fx-border-radius: 15px");
        }
    }

    public void redirectToSignUp() throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher("test");
        sceneSwitcher.switchScene("sign-up.fxml", emailField);
    }

    private void saveCookie(long id) throws IOException {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("cookie.txt"));
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            System.out.println("Error in writing to file: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
