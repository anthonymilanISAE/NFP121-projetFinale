package models;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class ManageData {

    private static ManageData dataManager;
    File directory = new File("data");

    public ManageData() {
    }

    public static ManageData getInstance() {
        if (dataManager == null) {
            dataManager = new ManageData();
        }

        return dataManager;
    }

    public void saveData(String filename, ArrayList<?> targetObjects, String errorMessage) {
        if (!directory.exists()) {
            directory.mkdir();
        }
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            File fileEns = new File(directory.getName() + filename);
            fileEns.createNewFile();
            fileOutputStream = new FileOutputStream(fileEns);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(targetObjects);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, errorMessage);
            return;
        }
    };
}
