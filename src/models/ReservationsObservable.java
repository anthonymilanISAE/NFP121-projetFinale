package models;

import java.util.Observable;

import javax.swing.JTextField;

public class ReservationsObservable extends Observable {
    public int value;
    JTextField field = new JTextField();

    public ReservationsObservable(int v) {
        value = v;
    }

    public JTextField createField() {
        field.setText(String.valueOf(value));
        return field;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        value = v;
        setChanged();
        notifyObservers(new Float(v));
    }
}