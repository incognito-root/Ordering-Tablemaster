package com.tablemasterordering.orderingtablemaster;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class MainController {

    @FXML
    private ImageView navDashboardActive;

    @FXML
    private Rectangle navDashboardBackground;

    @FXML
    private ImageView navDashboardInactive;

    @FXML
    private ImageView navHomeActive;

    @FXML
    private Rectangle navHomeBackground;

    @FXML
    private ImageView navHomeInactive;

    @FXML
    private ImageView navMessagesActive;

    @FXML
    private Rectangle navMessagesBackground;

    @FXML
    private ImageView navMessagesInactive;

    @FXML
    private ImageView navNotificationsActive;

    @FXML
    private Rectangle navNotificationsBackground;

    @FXML
    private ImageView navNotificationsInactive;

    @FXML
    private ImageView navSettingsActive;

    @FXML
    private Rectangle navSettingsBackground;

    @FXML
    private ImageView navSettingsInactive;

    @FXML
    void exitApp() {
        System.exit(0);
    }

    @FXML
    void navigateToPage(MouseEvent event) {
        String[] firstPart = event.getSource().toString().split("id=");
        String[] navItem = firstPart[1].split(",");

        deactivateMenuItems();

        switch (navItem[0]) {
            case "navHomeActive" -> {
                navHomeInactive.setVisible(true);
                navHomeBackground.setVisible(false);
                navHomeActive.setVisible(false);

            }
            case "navHomeInactive" -> {
                navHomeInactive.setVisible(false);
                navHomeBackground.setVisible(true);
                navHomeActive.setVisible(true);
            }

            case "navDashboardActive" -> {
                navDashboardInactive.setVisible(true);
                navDashboardBackground.setVisible(false);
                navDashboardActive.setVisible(false);
            }
            case "navDashboardInactive" -> {
                navDashboardInactive.setVisible(false);
                navDashboardBackground.setVisible(true);
                navDashboardActive.setVisible(true);
            }

            case "navMessagesActive" -> {
                navMessagesInactive.setVisible(true);
                navMessagesBackground.setVisible(false);
                navMessagesActive.setVisible(false);
            }
            case "navMessagesInactive" -> {
                navMessagesInactive.setVisible(false);
                navMessagesBackground.setVisible(true);
                navMessagesActive.setVisible(true);
            }

            case "navNotificationsActive" -> {
                navNotificationsInactive.setVisible(true);
                navNotificationsBackground.setVisible(false);
                navNotificationsActive.setVisible(false);
            }
            case "navNotificationsInactive" -> {
                navNotificationsInactive.setVisible(false);
                navNotificationsBackground.setVisible(true);
                navNotificationsActive.setVisible(true);
            }

            case "navSettingsActive" -> {
                navSettingsInactive.setVisible(true);
                navSettingsBackground.setVisible(false);
                navSettingsActive.setVisible(false);
            }
            case "navSettingsInactive" -> {
                navSettingsInactive.setVisible(false);
                navSettingsBackground.setVisible(true);
                navSettingsActive.setVisible(true);
            }
        }

    }

    void deactivateMenuItems() {
        navHomeActive.setVisible(false);
        navHomeBackground.setVisible(false);
        navHomeInactive.setVisible(true);

        navDashboardActive.setVisible(false);
        navDashboardBackground.setVisible(false);
        navDashboardInactive.setVisible(true);

        navMessagesActive.setVisible(false);
        navMessagesBackground.setVisible(false);
        navMessagesInactive.setVisible(true);

        navNotificationsActive.setVisible(false);
        navNotificationsBackground.setVisible(false);
        navNotificationsInactive.setVisible(true);

        navSettingsActive.setVisible(false);
        navSettingsBackground.setVisible(false);
        navSettingsInactive.setVisible(true);
    }

}