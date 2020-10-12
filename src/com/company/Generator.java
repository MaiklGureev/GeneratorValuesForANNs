package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    int countExperiments;
    boolean isTheftHouse;
    int totalCount;
    int countApartments;
    List<TypeApartment> typeApartmentListList = new ArrayList<>();
    List<Porch> porchList = new ArrayList<>();

    public Generator(int countExperiments) {
        this.countExperiments = countExperiments;
    }

    public void addNewTypeApartment(TypeApartment typeApartment) {
        typeApartmentListList.add(typeApartment);
    }

    public void addNewPorch(Porch simplePorch) {
        porchList.add(simplePorch);
    }

    public void init() {
        //подсчёт числа квартир разных типов
        for (TypeApartment typeApartment : typeApartmentListList) {
            countApartments += typeApartment.getCountAllApartments();
        }
        totalCount = 2 + 7 * (countApartments + 1);
    }

    public String[][] doExperiment() {

        init();

        String[][] values = new String[countExperiments][totalCount];
        double v;
        double totalSumOfTheDay = 0;
        int counterTheft = 0;
        int counter = 0;
        String progress;

        //цикл заполняющий строки
        for (int a = 0; a < countExperiments; a++) {

            int b = 0;

            //прогресс бар
            if (a % 150 == 0 || a == countExperiments - 1) {
                progress = String.format("%d/%d", a, countExperiments);
                System.out.println(progress);
            }

            boolean isTheftExperiment;
            if (Utils.getEvenDistributionRandomBoolean()) {
                values[a][b] = "1";
                isTheftExperiment = true;
                counterTheft++;
            } else {
                isTheftExperiment = false;
                values[a][b] = "0";
                counter++;
            }

            //выставляем параметр эксперимента (воровство/неворовство)
            for (int t = 0; t<typeApartmentListList.size();t++) {
                typeApartmentListList.get(t).setTheftExperiment(isTheftExperiment);
            }

            b++;

            values[a][b] = "st.ABL_" + new Random().nextInt(totalCount);
            b++;


            List<double[][]> tempMatrixList = new ArrayList<>();
            for (TypeApartment typeApartment : typeApartmentListList) {
                tempMatrixList.add(typeApartment.getStringMatrix());
            }

            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {

                //подъезд
                for (Porch p : porchList) {
                    v = p.calculateConsumption();
                    totalSumOfTheDay += v;
                }

                //квартиры
                for (double[][] var : tempMatrixList) {
                    int columns = var[0].length;
                    totalSumOfTheDay += var[dayOfWeek][columns - 1];
                    for (int j = 0; j < columns - 1; j++) {
                        values[a][b] = Utils.roundDouble(var[dayOfWeek][j]);
                        b++;
                    }
                }
                values[a][b] = Utils.roundDouble(totalSumOfTheDay);
                b++;
                totalSumOfTheDay = 0;

            }


        }
        System.out.println("Записей без воровства " + counter);
        System.out.println("Записей с воровством " + counterTheft);
        return values;
    }


}
