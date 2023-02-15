package models;

import java.io.Serializable;

public class Program implements Serializable {
    private static final long serialVersionUID = -5642978038203825702L;
    Activity act;
    Transportation trans;
    Accomodation acc;

    static int reservations;
    private static int id = 1;
    private int code;

    public Program(Activity activity, Transportation transport, Accomodation accom) {
        this(activity, transport, accom, 0);
    }

    public Program(Activity activity, Transportation transport, Accomodation accom, int reserve) {
        code = id++;
        act = activity;
        trans = transport;
        acc = accom;
        reservations = reserve;

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

    public int getReservations() {
        return reservations;
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

    public static void setReservations(int r) {
        reservations = r;
    }

    public static void addReservations(int r) {
        reservations += r;
    }

    public static void freeReservations(int r) {
        reservations -= r;
    }

    public String toString() {
        return " " + getId() + '-' + " Transportation: " + trans.getName() + '\n' + " Accomodation: "
                + getAccomodation()
                + "\n" + " Activity: " + getActivity();
    }
}
