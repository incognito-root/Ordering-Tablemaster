package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tablemasterordering.orderingtablemaster.models.*;

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

    public boolean saveCustomerAddress(SaveAddressRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(request);

        HttpResponse<String> response = postRequest("customer/saveCustomerAddress", reqBody);

        if (response.statusCode() == 201) {
            return true;
        }

        return false;
    }

    public DashboardModel getCustomerDashboardData(GetCustomerById customer) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        String reqBody = mapper.writeValueAsString(customer);

        HttpResponse<String> response = postRequest("customer/getDashboardData", reqBody);
        DashboardModel dashboardData = mapper.readValue(response.body(), DashboardModel.class);

        if (response.statusCode() == 403) {
            System.out.println("User Not Found");
        } else if (response.statusCode() == 401) {
            System.out.println("Wrong Credentials");
        } else if (response.statusCode() == 200) {
            return dashboardData;
        }

        return dashboardData;
    }

    public double getDiscount() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        HttpResponse<String> response = getRequest("discount/getActiveDiscount");

        DiscountModel discountModel = mapper.readValue(response.body(), DiscountModel.class);

        if (response.statusCode() == 403) {
            System.out.println("Bad Request");
        } else if (response.statusCode() == 200) {
            return discountModel.getDiscountAmount();
        }

        return 0;
    }
}
