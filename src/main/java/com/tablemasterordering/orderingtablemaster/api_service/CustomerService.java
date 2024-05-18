package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablemasterordering.orderingtablemaster.models.CustomerModel;
import com.tablemasterordering.orderingtablemaster.models.GetCustomerById;
import com.tablemasterordering.orderingtablemaster.models.LoginModel;
import com.tablemasterordering.orderingtablemaster.models.LoginResponseModel;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CustomerService extends MainService {

    public boolean customerSignUp(CustomerModel customer) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(customer);

        int result = postRequest("customer/createCustomerRecord", reqBody).statusCode();

        return result == 201; // CREATED
    }

    public LoginResponseModel customerLogin(LoginModel customer) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(customer);

        HttpResponse<String> response = postRequest("customer/loginCustomer", reqBody);
        LoginResponseModel customerDetails = mapper.readValue(response.body(), LoginResponseModel.class);

        if (response.statusCode() == 403) {
            System.out.println("User Not Found");
        } else if (response.statusCode() == 401) {
            System.out.println("Wrong Credentials");
        } else if (response.statusCode() == 200) {
            return customerDetails;
        }

        return customerDetails;
    }

    public CustomerModel getCustomerDetails(GetCustomerById customer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(customer);

        HttpResponse<String> response = postRequest("customer/getCustomerById", reqBody);
        CustomerModel customerDetails = mapper.readValue(response.body(), CustomerModel.class);

        if (response.statusCode() == 403) {
            System.out.println("User Not Found");
        } else if (response.statusCode() == 401) {
            System.out.println("Wrong Credentials");
        } else if (response.statusCode() == 200) {
            return customerDetails;
        }

        return customerDetails;
    }
}
