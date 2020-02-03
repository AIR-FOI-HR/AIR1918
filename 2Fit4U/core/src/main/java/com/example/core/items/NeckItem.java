package com.example.core.items;

public class NeckItem {

    private String neckValue;
    private String dateValue;

    public NeckItem(String neckValue, String dateValue) {
        this.neckValue = neckValue;
        this.dateValue = dateValue;
    }

    public String getNeckValue() {
        return neckValue;
    }

    public void setNeckValue(String neckValue) {
        this.neckValue = neckValue;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
}
