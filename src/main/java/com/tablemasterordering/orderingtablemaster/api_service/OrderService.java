package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablemasterordering.orderingtablemaster.models.LoginModel;
import com.tablemasterordering.orderingtablemaster.models.LoginResponseModel;
import com.tablemasterordering.orderingtablemaster.models.OrderModel;

import java.io.IOException;
import java.net.http.HttpResponse;

public class OrderService extends MainService {

    public boolean createOrder(OrderModel orderToCreate) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(orderToCreate);

        HttpResponse<String> response = postRequest("order/createOrder", reqBody);

        if (response.statusCode() == 403) {
            System.out.println("User Not Found");
            return false;
        } else if (response.statusCode() == 401) {
            System.out.println("Wrong Credentials");
            return false;
        } else return response.statusCode() == 201;
    }
}
