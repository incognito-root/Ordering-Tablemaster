package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.ComponentDetailsModel;
import com.tablemasterordering.orderingtablemaster.models.DiscountModel;
import com.tablemasterordering.orderingtablemaster.models.MenuItemModel;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CartController implements Initializable {
    @FXML
    private Text cartDiscount;

    @FXML
    private VBox cartItems;

    @FXML
    private Text cartSubTotal;

    @FXML
    private Text cartTax;

    @FXML
    private Text cartTotal;

    @FXML
    private AnchorPane mainCartArea;

    public CartController() {
    }

    public void fillData() throws IOException {

        ArrayList<MenuItemModel> dataList = getMenuItemModels();

        for (MenuItemModel details : dataList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-menu-item-card.fxml"));
            AnchorPane anchorPane = loader.load();
            CartMenuItemController paneController = loader.getController();

            paneController.setData(details.getTitle(), String.valueOf(details.getPrice()), "2", "100");
            cartItems.getChildren().add(anchorPane);
        }
    }

    private static ArrayList<MenuItemModel> getMenuItemModels() {
        ArrayList<MenuItemModel> dataList = new ArrayList<>();
        MenuItemModel m1 = new MenuItemModel("test recipe", 100);
        MenuItemModel m2 = new MenuItemModel("test recipe", 100);
        MenuItemModel m3 = new MenuItemModel("test recipe", 100);
        MenuItemModel m4 = new MenuItemModel("test recipe", 100);
        MenuItemModel m5 = new MenuItemModel("test recipe", 100);

        dataList.add(m1);
        dataList.add(m2);
        dataList.add(m3);
        dataList.add(m4);
        dataList.add(m5);
        return dataList;
    }

    public void closeCart() {
        this.mainCartArea.setVisible(false);

        Color color = new Color(0, 0, 0, 0);
        BackgroundFill fill = new BackgroundFill(color, null, null);
        Background background = new Background(fill);
        mainCartArea.setBackground(background);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            fillData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
