package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.api_service.MenuService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}