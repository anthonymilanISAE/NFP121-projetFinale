package models;

import java.util.*;

public class ReservationsObserver implements Observer {
    private float price;

    public ReservationsObserver() {
        price = 0;
    }

    public void update(Observable obj, Object arg) {
        if (arg instanceof Float) {
            price = (Float) arg;
        }
    }

    public float getPrice() {
        return price;
    }

}
