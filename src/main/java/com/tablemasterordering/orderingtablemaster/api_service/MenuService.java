package com.tablemasterordering.orderingtablemaster.api_service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tablemasterordering.orderingtablemaster.models.MenuItemModel;

import java.io.IOException;
import java.util.ArrayList;

public class MenuService extends MainService {

    private ArrayList<MenuItemModel> allMenuItems = new ArrayList<>();

    private void readAllMenuItems() throws IOException {
        String result = getRequest("menu/getAllMenuItems").body();

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        this.allMenuItems = mapper.readValue(result, new TypeReference<ArrayList<MenuItemModel>>() {
        });
    }

    public void setAllMenuItems() throws IOException {
        readAllMenuItems();
    }

    public ArrayList<MenuItemModel> getAllMenuItems() {
        return allMenuItems;
    }
}
