package com.company.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface Report {

    String getFilename();
    String fileContent();

    default void execute() {

        try {

            File file = new File(getFilename());
            if (!file.exists()) {
                System.out.println("File created: " + getFilename());
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Unexpected error occurred!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilename(), true))) {
            writer.write(fileContent());
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
