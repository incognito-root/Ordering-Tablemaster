package com.tablemasterordering.orderingtablemaster;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DetailsPaneController {

    @FXML
    private ImageView detailsImage;

    @FXML
    private Text detailsTextMain;

    @FXML
    private Text detailsTextSecondary;

    @FXML
    private ImageView detailsPaneArrow;

    public void setData(Image detailsImage, String detailsTextMain, String detailsTextSecondary) {
        this.detailsImage.setImage(detailsImage);
        this.detailsTextMain.setText(detailsTextMain);
        this.detailsTextSecondary.setText(detailsTextSecondary);
    }

    public ImageView getDetailsPaneArrow() {
        return detailsPaneArrow;
    }

    public void setDetailsPaneArrow(ImageView detailsPaneArrow) {
        this.detailsPaneArrow = detailsPaneArrow;
    }
}
