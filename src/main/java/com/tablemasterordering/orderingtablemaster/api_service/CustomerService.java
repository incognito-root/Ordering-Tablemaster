package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tablemasterordering.orderingtablemaster.helper_functions.Auth;
import com.tablemasterordering.orderingtablemaster.helper_functions.Popup;
import com.tablemasterordering.orderingtablemaster.helper_functions.PopupTypeEnum;
import com.tablemasterordering.orderingtablemaster.models.*;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CustomerService extends MainService {

    public boolean customerSignUp(CustomerModel customer) throws IOException {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String reqBody = mapper.writeValueAsString(customer);

        TypeReference<ApiResponse<CustomerModel>> typeRef = new TypeReference<>() {
        };

        HttpResponse<String> result = postRequest("customer/createCustomerRecord", reqBody);

        ApiResponse<CustomerModel> apiResponse = mapper.readValue(result.body(), typeRef);

        if (apiResponse.isSuccess()) {
            Popup.showPopup(PopupTypeEnum.INFO, apiResponse.getMessage(), "Sign Up Successful");
            return true;
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, apiResponse.getMessage(), "Sign Up Failed");
            return false;
        }
    }

    public Long customerLogin(LoginRequestModel customer) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(customer);

        HttpResponse<String> response = postRequest("customer/loginCustomer", reqBody);

        TypeReference<ApiResponse<Long>> typeRef = new TypeReference<>() {
        };

        ApiResponse<Long> customerDetails = mapper.readValue(response.body(), typeRef);

        if (customerDetails.isSuccess()) {

            return customerDetails.getData();
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, customerDetails.getMessage(), "Login Failed");
            return null;
        }
    }

    public CustomerModel getCustomerDetails(long customer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<ApiResponse<CustomerModel>> typeRef = new TypeReference<>() {
        };

        HttpResponse<String> response = getRequest("customer/getCustomerById/" + customer);

        ApiResponse<CustomerModel> customerDetails = mapper.readValue(response.body(), typeRef);

        if (customerDetails.isSuccess()) {
            return customerDetails.getData();
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, customerDetails.getMessage(), "Login Failed");
        }

        return null;
    }

    public boolean saveCustomerAddress(SaveAddressRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String reqBody = mapper.writeValueAsString(request);

        TypeReference<ApiResponse<Boolean>> typeRef = new TypeReference<>() {
        };
        HttpResponse<String> response = postRequest("customer/saveCustomerAddress", reqBody);

        ApiResponse<Boolean> apiResponse = mapper.readValue(response.body(), typeRef);

        if (apiResponse.isSuccess()) {
            Auth.customerDetails.setAddress(request.getAddress());
            return true;
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, apiResponse.getMessage(), "Save Address Failed");
        }

        return false;
    }

    public DashboardModel getCustomerDashboardData(long customer) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        TypeReference<ApiResponse<DashboardModel>> typeRef = new TypeReference<>() {
        };

        HttpResponse<String> response = getRequest("customer/getDashboardData/" + customer);
        ApiResponse<DashboardModel> dashboardData = mapper.readValue(response.body(), typeRef);

        if (dashboardData.isSuccess()) {
            return dashboardData.getData();
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, dashboardData.getMessage(), "Get Dashboard Data Failed");
            return null;
        }
    }

    public double getDiscount() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<ApiResponse<DiscountModel>> typeRef = new TypeReference<>() {
        };
        HttpResponse<String> response = getRequest("discount/getActiveDiscount");
        ApiResponse<DiscountModel> discountModel = mapper.readValue(response.body(), typeRef);

        if (discountModel.isSuccess()) {
            return discountModel.getData().getDiscountAmount();
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, discountModel.getMessage(), "Get Discount Failed");
            return 0;
        }
    }

    public OrderModel getMostFrequentOrder() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<ApiResponse<OrderModel>> typeRef = new TypeReference<>() {
        };

        HttpResponse<String> response = getRequest("customer/getCustomerMostFrequentOrder/" + Auth.customerDetails.getCustomerId());

        ApiResponse<OrderModel> orderModel = mapper.readValue(response.body(), typeRef);

        if (orderModel.isSuccess()) {
            return orderModel.getData();
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, orderModel.getMessage(), "Get Order Failed");
            return null;
        }
    }
}
