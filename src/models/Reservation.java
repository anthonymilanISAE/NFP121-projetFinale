package models;

import java.io.Serializable;

public class Reservation implements Serializable {
    private static int id = 1;
    Program program;
    User user;
    int nbOfReservations;
    boolean confirmed;

    public Reservation(Program prog, User username, boolean conf, int nbRes) {
        program = prog;
        user = username;
        confirmed = conf;
        nbOfReservations = nbRes;
    }

    public static void setCounter(int i) {
        id = i;
    }

    public boolean getConfirmation() {
        return confirmed;
    }

    public String toString() {
        return "User: " + user.toString() + " Number of Reservations: " + nbOfReservations;
    }
}
