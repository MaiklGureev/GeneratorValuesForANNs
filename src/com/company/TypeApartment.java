package com.company;

import java.util.Random;

public class TypeApartment {

    private Apartment[] apartments;
    private Apartment simpleApartment;
    private int countAllApartments = 0;
    private int countTheftApartments = 0;
    private boolean isTheftExperiment = false;

    private double[][] consumptionArray;

    public TypeApartment(int countAllApartments, int countTheftApartments, Apartment simpleApartment) {
        this.countAllApartments = countAllApartments;
        this.countTheftApartments = countTheftApartments;
        this.simpleApartment = simpleApartment;
    }

    public int getCountAllApartments() {
        return countAllApartments;
    }

    public void setTheftExperiment(boolean theftExperiment) {
        isTheftExperiment = theftExperiment;
    }

    public void init() {
        apartments = new Apartment[countAllApartments];
        consumptionArray = new double[7][countAllApartments + 1];

        for (int a = 0; a < countAllApartments; a++) {
            apartments[a] = new Apartment(
                    simpleApartment.getValueWeekdays(),
                    simpleApartment.getValueWeekend(),
                    simpleApartment.getValueTheft(),
                    simpleApartment.getDispersionWeekdays(),
                    simpleApartment.getDispersionWeekend(),
                    simpleApartment.getDispersionTheft()
            );
        }
        initTheftApartments();
    }

    public void initTheftApartments() {
        for (int a = 0; a < countTheftApartments; a++) {
            int numberTheftApartment = new Random().nextInt(countAllApartments);
            apartments[numberTheftApartment].setTheftApartment(true);
        }
    }

    public double[][] getStringMatrix() {

        double sum = 0;
        double val = 0;
        init();
        //цикл недели
        for (int a = 0; a < 7; a++) {

            //цикл квартир
            for (int b = 0; b < countAllApartments; b++) {
                if (isTheftExperiment) {
                    //проверка на неворовство/воровство
                    if (!apartments[b].isTheftApartment()) {
                        //проверка на день недели
                        if (!isWeekend(a)) {
                            val = apartments[b].calculateConsumptionWeekdaysNormal();
                        } else {
                            val = apartments[b].calculateConsumptionWeekendNormal();
                        }
                        consumptionArray[a][b] = val;
                        sum += val;
                    } else {
                        //проверка на день недели
                        if (!isWeekend(a)) {
                            val = apartments[b].calculateConsumptionWeekdaysTheft();
                            sum += apartments[b].calculateConsumptionWeekdaysNormal();
                        } else {
                            val = apartments[b].calculateConsumptionWeekendTheft();
                            sum += apartments[b].calculateConsumptionWeekendNormal();
                        }
                        consumptionArray[a][b] = val;
                    }
                }else{
                    //проверка на день недели
                    if (!isWeekend(a)) {
                        val = apartments[b].calculateConsumptionWeekdaysNormal();
                    } else {
                        val = apartments[b].calculateConsumptionWeekendNormal();
                    }
                    consumptionArray[a][b] = val;
                    sum += val;
                }


            }
            consumptionArray[a][countAllApartments] = sum;
            sum = 0;
        }
        return consumptionArray;
    }

    private boolean isWeekend(int index) {
        if (index == 5 || index == 6) {
            return true;
        } else {
            return false;
        }
    }

}
