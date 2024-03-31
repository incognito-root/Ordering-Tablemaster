package com.tablemasterordering.orderingtablemaster.models;

import javafx.scene.image.Image;

public class ComponentDetailsModel {
    private String titleText;
    private String descriptionText;
    private Image image;
    private String imageUrl;

    public ComponentDetailsModel(String titleText, String descriptionText, String imageUrl) {
        this.descriptionText = descriptionText;
        this.titleText = titleText;
        this.imageUrl = imageUrl;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
