package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.NavigationMenuItemModel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ImageView navDashboardActive, navDashboardInactive, navHomeActive, navHomeInactive,
            navMessagesActive, navMessagesInactive, navNotificationsActive, navNotificationsInactive,
            navSettingsActive, navSettingsInactive;

    @FXML
    private Rectangle navDashboardBackground, navHomeBackground, navMessagesBackground,
            navNotificationsBackground, navSettingsBackground;

    private final Map<String, String[]> navItems = new HashMap<>();

    private final SceneSwitcher sceneSwitcher;

    public MainController() {
        sceneSwitcher = new SceneSwitcher();
        initNavigationItems();
    }

    @FXML
    void exitApp() {
        System.exit(0);
    }

    @FXML
    void navigateToPage(MouseEvent event) throws IOException {
        String sourceId = ((ImageView) event.getSource()).getId();
        if (sourceId == null) return;

        deactivateMenuItems();

        String[] relatedItems = navItems.get(sourceId);
        if (relatedItems != null) {
            NavigationMenuItemModel navigationMenuItemModel = new NavigationMenuItemModel();
            navigationMenuItemModel.setInactiveImageView((ImageView) event.getSource());
            navigationMenuItemModel.setActiveImageView((ImageView) navSettingsInactive.getScene().lookup("#" + relatedItems[0]));
            navigationMenuItemModel.setBackgroundRectangle((Rectangle) navSettingsInactive.getScene().lookup("#" + relatedItems[1]));

            if (navigationMenuItemModel.getInactiveImageView().isVisible()) {
                navigationMenuItemModel.getActiveImageView().setVisible(true);
                navigationMenuItemModel.getBackgroundRectangle().setVisible(true);

                sceneSwitcher.switchScene(navigationMenuItemModel, mainBorderPane);
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
