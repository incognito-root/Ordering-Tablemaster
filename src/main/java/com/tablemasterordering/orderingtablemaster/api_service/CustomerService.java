package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablemasterordering.orderingtablemaster.models.CustomerModel;

import java.io.IOException;

public class CustomerService extends MainService {

    public boolean customerSignUp(CustomerModel customer) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(customer);

        int result = postRequest("customer/createCustomerRecord", reqBody).statusCode();

        return result == 201; // CREATED
    }
}
