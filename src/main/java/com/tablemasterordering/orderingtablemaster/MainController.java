package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.NavigationMenuItemModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ImageView navDashboardActive, navDashboardInactive, navHomeActive, navHomeInactive,
            navSettingsActive, navSettingsInactive;

    @FXML
    private Rectangle navDashboardBackground, navHomeBackground, navSettingsBackground;

    private final Map<String, String[]> navItems = new HashMap<>();

    private SceneSwitcher sceneSwitcher;

    public MainController() {

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

        navSettingsActive.setVisible(false);
        navSettingsInactive.setVisible(true);
        navSettingsBackground.setVisible(false);
    }

    private void initNavigationItems() {
        navItems.put("navHomeActive", new String[]{"navHomeInactive", "navHomeBackground"});
        navItems.put("navHomeInactive", new String[]{"navHomeActive", "navHomeBackground"});
        navItems.put("navDashboardActive", new String[]{"navDashboardInactive", "navDashboardBackground"});
        navItems.put("navDashboardInactive", new String[]{"navDashboardActive", "navDashboardBackground"});
        navItems.put("navSettingsActive", new String[]{"navSettingsInactive", "navSettingsBackground"});
        navItems.put("navSettingsInactive", new String[]{"navSettingsActive", "navSettingsBackground"});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneSwitcher = new SceneSwitcher();
        initNavigationItems();

        NavigationMenuItemModel navigationMenuItemModel = new NavigationMenuItemModel();
        navigationMenuItemModel.setInactiveImageView(navHomeInactive);
        navigationMenuItemModel.setActiveImageView(navHomeActive);
        navigationMenuItemModel.setBackgroundRectangle(navHomeBackground);

        try {
            sceneSwitcher.switchScene(navigationMenuItemModel, mainBorderPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
