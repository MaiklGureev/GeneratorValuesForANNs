package com.company;

public class Apartment {

    private boolean isTheftApartment;
    private double valueWeekdays;
    private double valueWeekend;
    private double valueTheft;
    private int dispersionWeekdays;
    private int dispersionWeekend;
    private int dispersionTheft;

    public Apartment(double valueWeekdays, double valueWeekend, double valueTheft, int dispersionWeekdays, int dispersionWeekend, int dispersionTheft) {
        this.valueWeekdays = valueWeekdays;
        this.valueWeekend = valueWeekend;
        this.valueTheft = valueTheft;
        this.dispersionWeekdays = dispersionWeekdays;
        this.dispersionWeekend = dispersionWeekend;
        this.dispersionTheft = dispersionTheft;
    }

    public void setTheftApartment(boolean theftApartment) {
        isTheftApartment = theftApartment;
    }

    public double calculateConsumptionWeekdaysNormal() {
        return Utils.getGaussianRandomValue(valueWeekdays, dispersionWeekdays);
    }

    public double calculateConsumptionWeekendNormal() {
        return Utils.getGaussianRandomValue(valueWeekend, dispersionWeekend);
    }

    public double calculateConsumptionWeekdaysTheft() {
        return Utils.getGaussianRandomValue(valueTheft, dispersionTheft);
    }

    public boolean isTheftApartment() {
        return isTheftApartment;
    }

    public double calculateConsumptionWeekendTheft() {
        return Utils.getGaussianRandomValue(valueTheft, dispersionTheft);
    }

    public double getValueWeekdays() {
        return valueWeekdays;
    }

    public double getValueWeekend() {
        return valueWeekend;
    }

    public double getValueTheft() {
        return valueTheft;
    }

    public int getDispersionWeekdays() {
        return dispersionWeekdays;
    }

    public int getDispersionWeekend() {
        return dispersionWeekend;
    }

    public int getDispersionTheft() {
        return dispersionTheft;
    }
}
