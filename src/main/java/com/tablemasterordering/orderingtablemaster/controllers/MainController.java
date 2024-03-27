package com.tablemasterordering.orderingtablemaster.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML
    private ImageView navDashboardActive, navDashboardInactive, navHomeActive, navHomeInactive,
            navMessagesActive, navMessagesInactive, navNotificationsActive, navNotificationsInactive,
            navSettingsActive, navSettingsInactive;

    @FXML
    private Rectangle navDashboardBackground, navHomeBackground, navMessagesBackground,
            navNotificationsBackground, navSettingsBackground;

    private final Map<String, String[]> navItems = new HashMap<>();

    public MainController() {
        initNavigationItems();
    }

    @FXML
    void exitApp() {
        System.exit(0);
    }

    @FXML
    void navigateToPage(MouseEvent event) {
        String sourceId = ((ImageView) event.getSource()).getId();
        if (sourceId == null) return;

        deactivateMenuItems();

        String[] relatedItems = navItems.get(sourceId);
        if (relatedItems != null) {
            ImageView activeImageView = (ImageView) event.getSource();
            ImageView inactiveImageView = (ImageView) navDashboardInactive.getScene().lookup("#" + relatedItems[0]);
            Rectangle backgroundRectangle = (Rectangle) navDashboardBackground.getScene().lookup("#" + relatedItems[1]);

            if (activeImageView.isVisible()) {
                activeImageView.setVisible(false);
                inactiveImageView.setVisible(true);
                backgroundRectangle.setVisible(true);
            }
        }
    }

    private void deactivateMenuItems() {
        navDashboardActive.setVisible(false);
        navDashboardInactive.setVisible(true);
        navDashboardBackground.setVisible(false);

        navHomeActive.setVisible(false);
        navHomeInactive.setVisible(true);
        navHomeBackground.setVisible(false);

        navMessagesActive.setVisible(false);
        navMessagesInactive.setVisible(true);
        navMessagesBackground.setVisible(false);

        navNotificationsActive.setVisible(false);
        navNotificationsInactive.setVisible(true);
        navNotificationsBackground.setVisible(false);

        navSettingsActive.setVisible(false);
        navSettingsInactive.setVisible(true);
        navSettingsBackground.setVisible(false);
    }

    private void initNavigationItems() {
        navItems.put("navHomeActive", new String[]{"navHomeInactive", "navHomeBackground"});
        navItems.put("navHomeInactive", new String[]{"navHomeActive", "navHomeBackground"});
        navItems.put("navDashboardActive", new String[]{"navDashboardInactive", "navDashboardBackground"});
        navItems.put("navDashboardInactive", new String[]{"navDashboardActive", "navDashboardBackground"});
        navItems.put("navMessagesActive", new String[]{"navMessagesInactive", "navMessagesBackground"});
        navItems.put("navMessagesInactive", new String[]{"navMessagesActive", "navMessagesBackground"});
        navItems.put("navNotificationsActive", new String[]{"navNotificationsInactive", "navNotificationsBackground"});
        navItems.put("navNotificationsInactive", new String[]{"navNotificationsActive", "navNotificationsBackground"});
        navItems.put("navSettingsActive", new String[]{"navSettingsInactive", "navSettingsBackground"});
        navItems.put("navSettingsInactive", new String[]{"navSettingsActive", "navSettingsBackground"});
    }
}
