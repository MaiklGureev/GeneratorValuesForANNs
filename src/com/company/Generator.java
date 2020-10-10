package com.company;

import java.text.DecimalFormat;
import java.util.Random;

public class Generator {

    int countExperiments;
    boolean isTheftInPorch;
    int totalCount;

    int countPorch;
    int countTypeApartmentOne;
    int countTypeApartmentTwo;
    int countTypeApartmentThree;
    int countTheftInPorch;

    double valuePorch;
    double valueTypeApartmentOne;
    double valueTypeApartmentTwo;
    double valueTypeApartmentThree;
    double valueTheftInPorch;

    double valueTypeApartmentOneWeekend;
    double valueTypeApartmentTwoWeekend;
    double valueTypeApartmentThreeWeekend;

    int dispersionPorch;
    int dispersionTypeOne;
    int dispersionTypeTwo;
    int dispersionTypeThree;

    int dispersionTypeTwoWeekend;
    int dispersionTypeOneWeekend;
    int dispersionTypeThreeWeekend;

    int dispersionTheftValueInPorch;

    public Generator() {
    }

    public void init() {
        totalCount = 2 + 7 * (countTypeApartmentOne + countTypeApartmentTwo + countTypeApartmentThree + 1);
    }

    public String[][] doExperiment() {
        init();

        String[][] values = new String[countExperiments][totalCount];
        double v;
        double totalSum = 0;
        int counterTheft = 0;
        int counter = 0;
        String progress;

        //цикл заполняющий строки
        for (int a = 0; a < countExperiments; a++) {
            
            totalSum = 0;
            
            //прогресс бар
            if (a % 150 == 0 || a==countExperiments-1) {
                progress = String.format("%d/%d",a,countExperiments);
                System.out.println(progress);
            }

            int b = 0;

            //подъезд
            for (int p = 0; p < countPorch; p++) {
                //цикл по неделе
                for (int w = 0; w < 7; w++) {
                    v = getGaussianRandomValue(valuePorch, dispersionPorch);
                    totalSum += v;
                }
            }

            if (isTheftInPorch) {
                if (getEvenDistributionRandomBoolean()) {
                    //цикл по ворующим подъездам
                    for (int p = 0; p < countTheftInPorch; p++) {
                        //цикл по неделе
                        for (int w = 0; w < 7; w++) {
                                v = getGaussianRandomValue(valueTheftInPorch, dispersionTheftValueInPorch);
                                totalSum += v;
                        }
                    }
                    values[a][b] = "1";
                    counterTheft++;
                }
                else {
                    values[a][b] = "0";
                    counter++;
                }
            }
            b++;

            values[a][b] = "st.ABL_" + new Random().nextInt(totalCount);
            b++;

            //цикл для будних
            for (int w = 0; w < 5; w++) {

                //первый тип квартир
                for (int i = 0; i < countTypeApartmentOne; i++) {
                    v = getGaussianRandomValue(valueTypeApartmentOne, dispersionTypeOne);
                    values[a][b] = String.valueOf(v);
                    totalSum += v;
                    b++;
                }
                //второй тип квартир
                for (int i = 0; i < countTypeApartmentTwo; i++) {
                    v = getGaussianRandomValue(valueTypeApartmentTwo, dispersionTypeTwo);
                    values[a][b] = String.valueOf(v);
                    totalSum += v;
                    b++;
                }
                //третий тип квартир
                for (int i = 0; i < countTypeApartmentThree; i++) {
                    v = getGaussianRandomValue(valueTypeApartmentThree, dispersionTypeThree);
                    values[a][b] = String.valueOf(v);
                    totalSum += v;
                    b++;
                }

                //итоговое значение за сутки
                values[a][b] = roundDouble(totalSum);
                b++;
            }

            //цикл для выходных
            for (int w = 0; w < 2; w++) {
                //первый тип квартир
                for (int i = 0; i < countTypeApartmentOne; i++) {
                    v = getGaussianRandomValue(valueTypeApartmentOneWeekend, dispersionTypeOneWeekend);
                    values[a][b] = String.valueOf(v);
                    totalSum += v;
                    b++;
                }
                //второй тип квартир
                for (int i = 0; i < countTypeApartmentTwo; i++) {
                    v = getGaussianRandomValue(valueTypeApartmentTwoWeekend, dispersionTypeTwoWeekend);
                    values[a][b] = String.valueOf(v);
                    totalSum += v;
                    b++;
                }
                //третий тип квартир
                for (int i = 0; i < countTypeApartmentThree; i++) {
                    v = getGaussianRandomValue(valueTypeApartmentThreeWeekend, dispersionTypeThreeWeekend);
                    values[a][b] = String.valueOf(v);
                    totalSum += v;
                    b++;
                }
                //итоговое значение за сутки
                values[a][b] = roundDouble(totalSum);
                b++;
            }
        }
        System.out.println("Записей без воровства "+counter);
        System.out.println("Записей с воровством "+counterTheft);
        return values;
    }


    public double getGaussianRandomValue(double value, int dispersion) {
        double a = value - value * dispersion / 100;
        a = a > 0 ? a : 0.01;
        double b = value + value * dispersion / 100;

        Random random = new Random();
        double random_num = a + random.nextGaussian() * (b - a);

        while (random_num < a || random_num > b) {
            random_num = getGaussianRandomValue(value, dispersion);
        }
//        System.out.println(String.valueOf(random_num));
        return Double.parseDouble(roundDouble(random_num));
    }

    public boolean getEvenDistributionRandomBoolean() {
        Random random = new Random();
        boolean random_val = random.nextBoolean();
        return random_val;
    }

    public String roundDouble(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String result = decimalFormat.format(value).replace(",", ".");
        return result;
    }


}
