package com.example.core.items;

public class WaistItem {

    private String waistValue;
    private String dateValue;

    public WaistItem(String waistValue, String dateValue) {
        this.waistValue = waistValue;
        this.dateValue = dateValue;
    }

    public String getWaistValue() {
        return waistValue;
    }

    public void setWaistValue(String waistValue) {
        this.waistValue = waistValue;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
}
