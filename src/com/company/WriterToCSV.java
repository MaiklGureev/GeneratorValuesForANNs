package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class WriterToCSV {


    public static void createCSV(String[][] matrix, String nameExperiment) {
        try {

            FileWriter writer = new FileWriter(nameExperiment + ".csv");

            for (int a = 0; a < matrix.length; a++) {
                for (int b = 0; b < matrix[a].length; b++) {
                    writer.append(matrix[a][b]);
                    if (b != matrix[a].length-1) {
                        writer.append(";");
                    }
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
