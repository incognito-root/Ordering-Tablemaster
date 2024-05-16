package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.helper_functions.InputValidations;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField ageField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label firstNameLabel;
    private String firstNameLabelBackup;

    @FXML
    private Label lastNameLabel;
    private String lastNameLabelBackup;

    @FXML
    private Label emailLabel;
    private String emailLabelBackup;

    @FXML
    private Label contactNumberLabel;
    private String contactNumberLabelBackup;

    @FXML
    private Label ageLabel;
    private String ageLabelBackup;

    @FXML
    private Label passwordLabel;
    private String passwordLabelBackup;

    @FXML
    private Label confirmPasswordLabel;
    private String confirmPasswordLabelBackup;

    @FXML
    private Button signUpButton;

    public void sendSignUpRequest() {
        System.out.println(signUpButton.disabledProperty().get());
    }

    public void validateField(Event e) {
        String source = ((TextField) e.getSource()).getId();

        switch (source) {
            case "firstNameField" -> handleFieldErrors(firstNameLabel, firstNameLabelBackup, firstNameField);

            case "lastNameField" -> handleFieldErrors(lastNameLabel, lastNameLabelBackup, lastNameField);

            case "emailField" -> {
                InputValidations.clearErrors(emailLabel, emailLabelBackup);

                if (!InputValidations.validateEmail(emailField.getText())) {
                    InputValidations.setErrors(emailLabel);
                    return;
                }

                InputValidations.clearErrors(emailLabel, emailLabelBackup);
            }

            case "contactNumberField" -> {
                InputValidations.clearErrors(contactNumberLabel, contactNumberLabelBackup);

                if (!InputValidations.isDigits(contactNumberField.getText())) {
                    InputValidations.setErrors(contactNumberLabel);
                    return;
                }

                if (!InputValidations.validateLength(contactNumberField.getText(), 11, 11)) {
                    InputValidations.setErrors(contactNumberLabel);
                    return;
                }

                InputValidations.clearErrors(contactNumberLabel, contactNumberLabelBackup);
            }

            case "ageField" -> {
                InputValidations.clearErrors(ageLabel, ageLabelBackup);

                if (!InputValidations.isDigits(ageField.getText())) {
                    InputValidations.setErrors(ageLabel);
                    return;
                }

                if (!InputValidations.validateLength(ageField.getText(), 2, 3)) {
                    InputValidations.setErrors(ageLabel);
                    return;
                }

                InputValidations.clearErrors(ageLabel, ageLabelBackup);
            }

            case "passwordField" -> {
                InputValidations.clearErrors(passwordLabel, passwordLabelBackup);

                if (!InputValidations.validateLength(passwordField.getText(), 8, 50)) {
                    InputValidations.setErrors(passwordLabel);
                    return;
                }

                InputValidations.clearErrors(passwordLabel, passwordLabelBackup);
            }

            case "confirmPasswordField" -> {
                InputValidations.clearErrors(confirmPasswordLabel, confirmPasswordLabelBackup);

                if (!InputValidations.validatePasswordMatch(passwordField.getText(), confirmPasswordField.getText())) {
                    InputValidations.setErrors(confirmPasswordLabel);
                    return;
                }

                InputValidations.clearErrors(confirmPasswordLabel, confirmPasswordLabelBackup);
            }
        }
    }

    private void handleFieldErrors(Label nameLabel, String firstNameLabelBackup, TextField firstNameField) {
        InputValidations.clearErrors(nameLabel, firstNameLabelBackup);

        if (!InputValidations.validateAlpha(firstNameField.getText())) {
            InputValidations.setErrors(nameLabel);
            disableButton();
            return;
        }
        if (!InputValidations.validateLength(firstNameField.getText(), 3, 30)) {
            InputValidations.setErrors(nameLabel);
            disableButton();
            return;
        }

        enableButton();
        InputValidations.clearErrors(nameLabel, firstNameLabelBackup);
    }

    private void disableButton() {
        signUpButton.setDisable(true);
    }

    private void enableButton() {
        signUpButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameLabelBackup = "First Name";
        lastNameLabelBackup = "Last Name";
        emailLabelBackup = "Email";
        contactNumberLabelBackup = "Contact Number";
        ageLabelBackup = "Age";
        passwordLabelBackup = "Password";
        confirmPasswordLabelBackup = "Confirm Password";
    }
}
