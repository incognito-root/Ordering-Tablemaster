package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.MenuItemModel;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeController implements Initializable {

    @FXML
    TilePane menuItemsArea;

    @FXML
    AnchorPane mainHomeArea;

    @FXML
    StackPane mainStackPane;

    private AnchorPane mainCartArea;

    public HomeController() {

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

    private void initializeCart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
        mainCartArea = loader.load();

        mainCartArea.setVisible(false);
        mainStackPane.getChildren().add(mainCartArea);
    }

    public void showCart() {
        mainCartArea.setVisible(true);
        mainCartArea.setTranslateX(mainCartArea.getBoundsInParent().getWidth());
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), mainCartArea);
        transition.setToX(0);
        transition.play();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> Platform.runLater(() -> {

            Color color = new Color(0, 0, 0, 0.6);
            BackgroundFill fill = new BackgroundFill(color, null, null);
            Background background = new Background(fill);
            mainCartArea.setBackground(background);

        }), 1, TimeUnit.SECONDS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.fillData();
            initializeCart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
