package com.example.core.items;

public class WeightItem {

    private String weightValue;
    private String dateValue;

    public WeightItem(String weightValue, String dateValue) {
        this.weightValue = weightValue;
        this.dateValue = dateValue;
    }

    public String getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(String weightValue) {
        this.weightValue = weightValue;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
}
