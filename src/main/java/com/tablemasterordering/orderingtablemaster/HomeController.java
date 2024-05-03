package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.MenuService;
import com.tablemasterordering.orderingtablemaster.models.CartMenuItemModel;
import com.tablemasterordering.orderingtablemaster.models.MenuItemModel;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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

    private static ArrayList<MenuItemModel> allMenuItems;

    public HomeController() {

    }

    public void fillData() throws IOException {

        for (MenuItemModel details : allMenuItems) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-item-card.fxml"));
            AnchorPane anchorPane = loader.load();
            MenuItemController paneController = loader.getController();

            paneController.setData(String.valueOf(details.getMenuItemPrice()), details.getMenuItemName(), details.getMenuItemImage());

            CartMenuItemModel cartMenuItemModel = new CartMenuItemModel(details);
            paneController.setSelectedCartMenuItem(cartMenuItemModel);

            menuItemsArea.getChildren().add(anchorPane);
        }

    }

    private void initializeCart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
        mainCartArea = loader.load();

        mainCartArea.setVisible(false);
        mainStackPane.getChildren().add(mainCartArea);
    }

    public void showCart() throws IOException {
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

        System.out.println();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            MenuService menuService = new MenuService();
            menuService.setAllMenuItems();
            allMenuItems  = menuService.getAllMenuItems();

            this.fillData();
            initializeCart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
