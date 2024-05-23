package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tablemasterordering.orderingtablemaster.helper_functions.Popup;
import com.tablemasterordering.orderingtablemaster.helper_functions.PopupTypeEnum;
import com.tablemasterordering.orderingtablemaster.models.ApiResponse;
import com.tablemasterordering.orderingtablemaster.models.MenuItemModel;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class MenuService extends MainService {

    private ArrayList<MenuItemModel> allMenuItems = new ArrayList<>();

    private void readAllMenuItems() throws IOException {
//        this.allMenuItems = mapper.readValue(result, new TypeReference<ArrayList<MenuItemModel>>() {
//        });

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        TypeReference<ApiResponse<List<MenuItemModel>>> typeReference = new TypeReference<ApiResponse<List<MenuItemModel>>>() {};
        HttpResponse<String> result = getRequest("menu/getAllMenuItems");
        ApiResponse<List<MenuItemModel>> data = mapper.readValue(result.body(), typeReference);

        if (data.isSuccess()) {
            this.allMenuItems = (ArrayList<MenuItemModel>) data.getData();
        } else {
            Popup.showPopup(PopupTypeEnum.ERROR, data.getMessage(), "Failed to get all menu items");
        }
    }

    public void setAllMenuItems() throws IOException {
        readAllMenuItems();
    }

    public ArrayList<MenuItemModel> getAllMenuItems() {
        return allMenuItems;
    }
}
