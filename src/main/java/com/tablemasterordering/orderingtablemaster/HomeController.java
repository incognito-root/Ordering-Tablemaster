package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.MenuItemModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeController {

    @FXML
    TilePane menuItemsArea;

    public HomeController() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> Platform.runLater(() -> {
            try {
                fillData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }), 3, TimeUnit.SECONDS);
    }

    public void fillData() throws IOException {
        ArrayList<MenuItemModel> dataList = new ArrayList<>();

        MenuItemModel m1 = new MenuItemModel("burger", 12);
        MenuItemModel m2 = new MenuItemModel("pizza", 15);
        MenuItemModel m3 = new MenuItemModel("shake", 20);
        MenuItemModel m4 = new MenuItemModel("pizza burger", 30);
        MenuItemModel m5 = new MenuItemModel("burger", 12);
        MenuItemModel m6 = new MenuItemModel("pizza", 15);
        MenuItemModel m7 = new MenuItemModel("shake", 20);
        MenuItemModel m8 = new MenuItemModel("pizza burger", 30);

        dataList.add(m1);
        dataList.add(m2);
        dataList.add(m3);
        dataList.add(m4);
        dataList.add(m5);
        dataList.add(m6);
        dataList.add(m7);
        dataList.add(m8);

        for (MenuItemModel details : dataList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-item-card.fxml"));
            AnchorPane anchorPane = loader.load();
            MenuItemController paneController = loader.getController();

            paneController.setData(String.valueOf(details.getPrice()), details.getTitle());
            menuItemsArea.getChildren().add(anchorPane);
        }
    }
}
