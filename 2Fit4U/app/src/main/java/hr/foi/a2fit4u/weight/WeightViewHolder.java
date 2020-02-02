package hr.foi.a2fit4u.weight;

public class WeightViewHolder {

    private String weightValue;
    private String dateValue;

    public WeightViewHolder(String weightValue, String dateValue) {
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
