package com.company;

public class Porch {

    double valuePorch;
    int dispersionPorch;

    public Porch(double valuePorch, int dispersionPorch) {
        this.valuePorch = valuePorch;
        this.dispersionPorch = dispersionPorch;
    }

    public double calculateConsumption() {
        return Utils.getGaussianRandomValue(valuePorch, dispersionPorch);
    }
}
