package com.example.core.items;

public class HipItem {

    private String hipValue;
    private String dateValue;

    public HipItem(String hipValue, String dateValue) {
        this.hipValue = hipValue;
        this.dateValue = dateValue;
    }

    public String getHipValue() {
        return hipValue;
    }

    public void setHipValue(String hipValue) {
        this.hipValue = hipValue;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
}
