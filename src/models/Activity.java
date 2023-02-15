package models;

import java.io.Serializable;

public class Activity implements Serializable {
    private String name;
    private String description;
    private double price;
    private static int id = 1;
    private int code;

    public Activity(String n, double p, String desc) {
        code = id++;
        name = n;
        price = p;
        description = desc;
    }

    // Getters

    public int getId() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    public static void setCounter(int i) {
        id = i;
    }

    public String toString() {
        return " " + getId() + '-' + ' ' + getName() + ' ' + getPrice() + '$';
    }
}
