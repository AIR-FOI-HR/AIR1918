package com.example.core.items;

public class MeasurementItem {

    private String neckValue;
    private String hipValue;
    private String waistValue;
    private String dateValue;

    public MeasurementItem(String neckValue, String hipValue, String waistValue, String dateValue) {
        this.neckValue = neckValue;
        this.hipValue = hipValue;
        this.waistValue = waistValue;
        this.dateValue = dateValue;
    }

    public String getNeckValue() {
        return neckValue;
    }

    public void setNeckValue(String neckValue) {
        this.neckValue = neckValue;
    }

    public String getHipValue() {
        return hipValue;
    }

    public void setHipValue(String hipValue) {
        this.hipValue = hipValue;
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
