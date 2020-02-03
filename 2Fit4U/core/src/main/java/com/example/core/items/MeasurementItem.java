package com.example.core.items;

public class MeasurementItem {

    private String type;
    private String value;
    private String dateValue;

    public MeasurementItem(String type, String value, String dateValue) {
        this.type = type;
        this.value = value;
        this.dateValue = dateValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
}
