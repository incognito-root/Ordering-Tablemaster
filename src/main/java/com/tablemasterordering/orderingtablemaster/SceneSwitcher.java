package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.NavigationMenuItemModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SceneSwitcher {
    public Map<String, String> sceneMap;

    public SceneSwitcher() {
        sceneMap = new HashMap<>(Map.ofEntries(
                Map.entry("navHomeInactive", "home.fxml"),
                Map.entry("navDashboardInactive", "dashboard.fxml"),
                Map.entry("navMessagesInactive", "messages.fxml"),
                Map.entry("navNotificationsInactive", "notifications.fxml"),
                Map.entry("navSettingsInactive", "settings.fxml")
        ));
    }

    public void switchScene(NavigationMenuItemModel navigationItem, BorderPane borderPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneMap.get(navigationItem.getInactiveImageView().getId())));
        AnchorPane anchorPane;
        anchorPane = loader.load();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> Platform.runLater(() -> {
            navigationItem.getActiveImageView().setVisible(true);
            navigationItem.getBackgroundRectangle().setVisible(true);
            borderPane.setRight(anchorPane);
        }), 0, TimeUnit.SECONDS);
    }
}
