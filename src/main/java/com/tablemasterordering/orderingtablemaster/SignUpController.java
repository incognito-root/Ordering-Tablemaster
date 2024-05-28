package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.CustomerService;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.helper_functions.InputValidations;
import com.tablemasterordering.orderingtablemaster.helper_functions.Popup;
import com.tablemasterordering.orderingtablemaster.helper_functions.PopupTypeEnum;
import com.tablemasterordering.orderingtablemaster.models.CustomerModel;
import com.tablemasterordering.orderingtablemaster.models.LoginModel;
import com.tablemasterordering.orderingtablemaster.models.LoginResponseModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField dateOfBirthField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

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
    private Label dateOfBirthLabel;
    private String dateOfBirthBackup;

    @FXML
    private Label passwordLabel;
    private String passwordLabelBackup;

    @FXML
    private Label confirmPasswordLabel;
    private String confirmPasswordLabelBackup;

    @FXML
    private Button signUpButton;

    @FXML
    private RadioButton radioButtonFemale;

    @FXML
    private RadioButton radioButtonMale;

    private String gender;

    @FXML
    private DatePicker datePicker;

    public void sendSignUpRequest() {
        CustomerModel customer = new CustomerModel(firstNameField.getText(), lastNameField.getText(), contactNumberField.getText(), "", emailField.getText(), passwordField.getText(), "", gender, datePicker.getValue().toString());

        try {
            CustomerService customerService = new CustomerService();
            boolean signedUp = customerService.customerSignUp(customer);

            if (!signedUp) {
                return;
            }

            LoginResponseModel c = customerService.customerLogin(new LoginModel(customer.getEmail(), customer.getPassword()));

            Auth.setCustomerDetails(c.getId());

            if (signedUp) {
                redirectToAddressSelection();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void redirectToLogin() throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher("test");
        sceneSwitcher.switchScene("login.fxml", emailField);
    }

    public void redirectToAddressSelection() throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher("test");
        sceneSwitcher.switchScene("address-after-signup-page.fxml", emailField);
    }

    public void validateField(Event e) {

        String source = ((Node) e.getSource()).getId();

        switch (source) {
            case "firstNameField" -> handleFieldErrors(firstNameLabel, firstNameLabelBackup, firstNameField);

            case "lastNameField" -> handleFieldErrors(lastNameLabel, lastNameLabelBackup, lastNameField);

            case "emailField" -> {
                InputValidations.clearErrors(emailLabel, emailLabelBackup);

                if (!InputValidations.validateEmail(emailField.getText())) {
                    InputValidations.setErrors(emailLabel);
                    disableButton();
                    return;
                }

                enableButton();
                InputValidations.clearErrors(emailLabel, emailLabelBackup);
            }

            case "contactNumberField" -> {
                InputValidations.clearErrors(contactNumberLabel, contactNumberLabelBackup);

                if (!InputValidations.isDigits(contactNumberField.getText())) {
                    InputValidations.setErrors(contactNumberLabel);
                    disableButton();
                    return;
                }

                if (!InputValidations.validateLength(contactNumberField.getText(), 11, 11)) {
                    InputValidations.setErrors(contactNumberLabel);
                    disableButton();
                    return;
                }

                enableButton();
                InputValidations.clearErrors(contactNumberLabel, contactNumberLabelBackup);
            }

            case "datePicker" -> {
                InputValidations.clearErrors(dateOfBirthLabel, dateOfBirthBackup);

                if (!InputValidations.validateAge(datePicker.getValue())) {
                    InputValidations.setErrors(dateOfBirthLabel);
                    disableButton();
                    return;
                }

                dateOfBirthField.setText(datePicker.getValue().toString());
                enableButton();
                InputValidations.clearErrors(dateOfBirthLabel, dateOfBirthBackup);
            }

            case "passwordField" -> {
                InputValidations.clearErrors(passwordLabel, passwordLabelBackup);

                if (!InputValidations.validateLength(passwordField.getText(), 8, 50)) {
                    InputValidations.setErrors(passwordLabel);
                    disableButton();
                    return;
                }

                enableButton();
                InputValidations.clearErrors(passwordLabel, passwordLabelBackup);
            }

            case "confirmPasswordField" -> {
                InputValidations.clearErrors(confirmPasswordLabel, confirmPasswordLabelBackup);

                if (!InputValidations.validatePasswordMatch(passwordField.getText(), confirmPasswordField.getText())) {
                    InputValidations.setErrors(confirmPasswordLabel);
                    disableButton();
                    return;
                }

                enableButton();
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
        if (!checkEmptyFields() &&
                InputValidations.validateEmail(emailField.getText()) &&
                InputValidations.isDigits(contactNumberField.getText()) && InputValidations.validateLength(contactNumberField.getText(), 11, 11) &&
                InputValidations.validateAge(datePicker.getValue()) &&
                InputValidations.validateLength(passwordField.getText(), 8, 50) &&
                InputValidations.validatePasswordMatch(passwordField.getText(), confirmPasswordField.getText()) &&
                InputValidations.validateAlpha(firstNameField.getText()) && InputValidations.validateLength(firstNameField.getText(), 3, 30) &&
                InputValidations.validateAlpha(lastNameField.getText()) && InputValidations.validateLength(lastNameField.getText(), 3, 30)) {
            signUpButton.setDisable(false);
        } else {
            signUpButton.setDisable(true);
        }
    }

    public void selectGender(ActionEvent actionEvent) {
        String source = ((RadioButton) actionEvent.getSource()).getId();

        if (source.equals("radioButtonMale")) {
            gender = "male";
        }
        if (source.equals("radioButtonFemale")) {
            gender = "female";
        }
    }

    public boolean checkEmptyFields() {
        return this.emailField.getText().isEmpty() ||
                this.passwordField.getText().isEmpty() ||
                this.firstNameField.getText().isEmpty() ||
                this.lastNameField.getText().isEmpty() ||
                this.contactNumberField.getText().isEmpty() ||
                this.confirmPasswordField.getText().isEmpty();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameLabelBackup = "First Name";
        lastNameLabelBackup = "Last Name";
        emailLabelBackup = "Email";
        contactNumberLabelBackup = "Contact Number";
        dateOfBirthBackup = "Date Of Birth";
        passwordLabelBackup = "Password";
        confirmPasswordLabelBackup = "Confirm Password";
        gender = "male";
        this.disableButton();
    }
}
