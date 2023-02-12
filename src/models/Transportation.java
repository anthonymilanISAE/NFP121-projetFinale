package models;

import java.io.Serializable;

public class Transportation implements Serializable {
    private String name;
    private double price;
    private static int id = 1;
    private int code;

    public Transportation(String n, double p) {
        code = id++;
        name = n;
        price = p;
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

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public static void setCounter(int i) {
        id = i;
    }

    public String toString() {
        return " " + getId() + '-' + ' ' + getName() + ' ' + getPrice();
    }
}
