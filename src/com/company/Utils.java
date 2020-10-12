package com.company;

import java.text.DecimalFormat;
import java.util.Random;

public class Utils {

    public static double getGaussianRandomValue(double value, int dispersion) {
        double a = value - value * dispersion / 100;
        a = a > 0 ? a : 0.01;
        double b = value + value * dispersion / 100;

        Random random = new Random();
        double random_num = a + random.nextGaussian() * (b - a);

        while (random_num < a || random_num > b) {
            random_num = getGaussianRandomValue(value, dispersion);
        }
        return Double.parseDouble(roundDouble(random_num));
    }

    public static boolean getEvenDistributionRandomBoolean() {
        Random random = new Random();
        boolean random_val = random.nextBoolean();
        return random_val;
    }

    public static String roundDouble(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String result = decimalFormat.format(value).replace(",", ".");
        return result;
    }
}
