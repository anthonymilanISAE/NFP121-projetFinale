package models;

import java.util.Date;

public class User {
    String fName;
    String lName;
    float balance;
    Date birthDate;

    public User(String fN, String lN, float b, Date bD) {
        fName = fN;
        lName = lN;
        balance = b < 5000 ? 5000 : b;
        birthDate = bD;
    }

    // getters
    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public float getBalance() {
        return balance;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    // setters

    public void setFirstName(String name) {
        fName = name;
    }

    public void setLastName(String name) {
        lName = name;
    }

    public void setBalance(float bal) {
        balance = bal < 5000 ? 5000 : bal;
    }

    public void setBirthDate(Date date) {
        birthDate = date;
    }

    public String toString() {
        return getFirstName() + ' ' + getLastName();

    }

}
