package models;

import java.io.Serializable;

public class Accomodation implements Serializable {
    private static int id = 1;
    private int code;

    private String name;
    private String location;
    private double price;
    private int stars;

    public Accomodation(String n, double p, int s, String loc) {
        code = id++;
        name = n;
        price = p;
        location = loc;
        stars = s;
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

    public int getStars() {
        return stars;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setStars(int newStars) {
        this.stars = newStars;
    }

    public void setLocation(String newDesc) {
        this.location = newDesc;
    }

    public static void setCounter(int i) {
        id = i;
    }

    public String toString() {
        return " " + getId() + '-' + ' ' + getName() + ' ' + getPrice();
    }
}
