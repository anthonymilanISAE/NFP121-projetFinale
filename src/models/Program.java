package models;

import java.io.Serializable;

public class Program implements Serializable {
    Activity act;
    Transportation trans;
    Accomodation acc;

    private static int id = 1;
    private int code;

    public Program(Activity activity, Transportation transport, Accomodation accom) {
        code = id++;
        act = activity;
        trans = transport;
        acc = accom;
    }

    public int getId() {
        return code;
    }

    public Activity getActivity() {
        return act;
    }

    public Transportation getTransportation() {
        return trans;
    }

    public Accomodation getAccomodation() {
        return acc;
    }

    // Setters
    public void setActivity(Activity act) {
        this.act = act;
    }

    public void setAccomodation(Accomodation acc) {
        this.acc = acc;
    }

    public void setTransportation(Transportation trans) {
        this.trans = trans;
    }

    public static void setCounter(int i) {
        id = i;
    }

    public String toString() {
        return " " + getId() + '-' + " Transportation: " + trans.getName() + '\n' + " Accomodation: "
                + getAccomodation()
                + "\n" + " Activity: " + getActivity();
    }
}
