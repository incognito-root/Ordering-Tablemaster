package com.tablemasterordering.orderingtablemaster.helper_functions;

import javafx.scene.control.Alert;

public class Popup {

    public static void showPopup(PopupTypeEnum type, String message, String title) {
        Alert alert = null;

        if (type.equals(PopupTypeEnum.ERROR)) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else if (type.equals(PopupTypeEnum.WARNING)) {
            alert = new Alert(Alert.AlertType.WARNING);
        } else if (type.equals(PopupTypeEnum.INFO)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }

        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
