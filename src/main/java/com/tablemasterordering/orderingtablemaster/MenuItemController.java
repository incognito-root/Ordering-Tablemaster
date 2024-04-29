package com.tablemasterordering.orderingtablemaster;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MenuItemController {
    @FXML
    private ImageView menuItemImage;

    @FXML
    private Text menuItemPrice;

    @FXML
    private Text menuItemTitle;

    private final String path = "/Users/ayaan/MyProjects/Ordering-Tablemaster";

    public void setData(String menuItemPrice, String menuItemTitle, String menuItemImage) throws FileNotFoundException {
        this.setMenuItemImage(menuItemImage);
        this.menuItemPrice.setText(menuItemPrice);
        this.menuItemTitle.setText(menuItemTitle);
    }

    public ImageView getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(String menuItemImage) throws FileNotFoundException {
        if (menuItemImage != null) {
            FileInputStream fis = new FileInputStream(path + "/src/main/resources/com/tablemasterordering/images/burger-demo.png");
            Image image = new Image(fis);
            this.menuItemImage.setImage(image);
        }
    }

    public Text getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(Text menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public Text getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(Text menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
    }
}
