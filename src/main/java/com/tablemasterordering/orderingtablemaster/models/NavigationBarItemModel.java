package com.tablemasterordering.orderingtablemaster.models;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class NavigationBarItemModel {
    ImageView inactiveImageView;
    ImageView activeImageView;
    Rectangle backgroundRectangle;

    public ImageView getInactiveImageView() {
        return inactiveImageView;
    }

    public void setInactiveImageView(ImageView inactiveImageView) {
        this.inactiveImageView = inactiveImageView;
    }

    public ImageView getActiveImageView() {
        return activeImageView;
    }

    public void setActiveImageView(ImageView activeImageView) {
        this.activeImageView = activeImageView;
    }

    public Rectangle getBackgroundRectangle() {
        return backgroundRectangle;
    }

    public void setBackgroundRectangle(Rectangle backgroundRectangle) {
        this.backgroundRectangle = backgroundRectangle;
    }
}
