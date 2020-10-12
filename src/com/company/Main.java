package com.company;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //System.out.println(new Generator().getRandomValue(4,30));

        Generator generator = new Generator(10000);
        Porch simplePorch = new Porch(2.5,5);
        Apartment simpleApartment = new Apartment(12, 12, 2, 10, 10, 10);
        TypeApartment typeApartment = new TypeApartment(100, 5, simpleApartment);
        generator.addNewTypeApartment(typeApartment);
        generator.addNewPorch(simplePorch);
        generator.addNewPorch(simplePorch);
        generator.addNewPorch(simplePorch);


        String fileName = addFileName();

        Runnable task = () -> {
            System.out.println("Выполнение генерации...");
            WriterToCSV.createCSV(generator.doExperiment(), fileName);
            System.out.println("Готово!");
        };
        Thread thread = new Thread(task);
        thread.start();



        //проверка TypeApartment
//        Apartment simpleApartment = new Apartment(12, 15, 4, 10, 10, 10);
//        TypeApartment typeApartment = new TypeApartment(5, 2, simpleApartment);
//        double[][] matrx = typeApartment.getStringMatrix();
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.print(matrx[i][j] + " ");
//            }
//            System.out.println();
//        }

    }

    static String addFileName() {
        Date date = new Date();
        String fileName = date.toString();
        fileName = fileName.replace(":", " ");
        return fileName;
    }


}
