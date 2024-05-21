package com.tablemasterordering.orderingtablemaster.models;

public class DiscountModel {
    private long discountId;
    private double discountAmount;
    private String discountTitle;

    public DiscountModel(long discountId, double discountAmount, String discountTitle) {
        this.discountId = discountId;
        this.discountAmount = discountAmount;
        this.discountTitle = discountTitle;
    }

    public DiscountModel() {
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }
}
