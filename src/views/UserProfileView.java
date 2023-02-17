package views;

import javax.swing.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

import models.User;

public class UserProfileView {
    public JPanel mainPanel;
    private JTextField firstNameTextField, lastNameTextField, currentBalanceTextField, birthField;
    JLabel labelFirstName, labelLastName, labelBalance, labelBd;

    Calendar myCalendar = new GregorianCalendar(2001, 11, 26);
    Date myDate = myCalendar.getTime();
    private User user = User.getInstance("Anthony", "Milan", 6500, myDate);

    public UserProfileView() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(1, 1, 0));

        // First Name
        labelFirstName = new JLabel("First Name");
        labelFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelFirstName.setBounds(10, 72, 49, 31);
        mainPanel.add(labelFirstName);

        firstNameTextField = new JTextField(user.getFirstName());
        firstNameTextField.setEditable(false);
        mainPanel.add(firstNameTextField);
        firstNameTextField.setColumns(10);

        // Last Name
        labelLastName = new JLabel("Last Name");
        labelLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mainPanel.add(labelLastName);

        lastNameTextField = new JTextField(user.getLastName());
        lastNameTextField.setBounds(101, 110, 200, 20);
        lastNameTextField.setEditable(false);
        mainPanel.add(lastNameTextField);
        lastNameTextField.setColumns(10);

        // Balance
        labelBalance = new JLabel("Balance");
        labelBalance.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mainPanel.add(labelBalance);

        currentBalanceTextField = new JTextField(String.valueOf(user.getBalance() + "$"));
        currentBalanceTextField.setBounds(101, 110, 200, 20);
        currentBalanceTextField.setEditable(false);
        mainPanel.add(currentBalanceTextField);
        currentBalanceTextField.setColumns(10);

        // Birth Day
        labelBd = new JLabel("Birth Day");
        labelBd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mainPanel.add(labelBd);

        birthField = new JTextField(new SimpleDateFormat("dd/MM/YYYY").format(user.getBirthDate()));
        birthField.setBounds(101, 110, 200, 20);
        birthField.setEditable(false);
        mainPanel.add(birthField);
        birthField.setColumns(10);

    }
}
