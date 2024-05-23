package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablemasterordering.orderingtablemaster.helper_functions.Popup;
import com.tablemasterordering.orderingtablemaster.helper_functions.PopupTypeEnum;
import com.tablemasterordering.orderingtablemaster.models.ApiResponse;
import com.tablemasterordering.orderingtablemaster.models.LoginModel;
import com.tablemasterordering.orderingtablemaster.models.LoginResponseModel;
import com.tablemasterordering.orderingtablemaster.models.OrderModel;

import java.io.IOException;
import java.net.http.HttpResponse;

public class OrderService extends MainService {

    public boolean createOrder(OrderModel orderToCreate) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String reqBody = mapper.writeValueAsString(orderToCreate);

        TypeReference<ApiResponse<Boolean>> typeRef = new TypeReference<ApiResponse<Boolean>>() {};
        HttpResponse<String> response = postRequest("order/createOrder", reqBody);
        ApiResponse<Boolean> data = mapper.readValue(response.body(), typeRef);

        if (data.isSuccess()) {
            Popup.showPopup(PopupTypeEnum.INFO, data.getMessage(), "Order placed");
            return true;
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, data.getMessage(), "Error occurred");
            return false;
        }
    }
}
