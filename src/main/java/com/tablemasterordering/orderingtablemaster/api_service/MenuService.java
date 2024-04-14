package com.tablemasterordering.orderingtablemaster.api_service;

import java.io.IOException;

public class MenuService extends MainService {

    public void getAllMenuItems() throws IOException {
        System.out.println(getRequest("menu/getAllMenuItems").body());
    }

}
