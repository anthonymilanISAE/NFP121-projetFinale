package controllers;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frames.AgentFrame;
import models.ManageData;
import models.Reservation;
import views.ReservationsView;

public class ReservationsController implements ActionListener, ListSelectionListener {
    public JPanel mainPanel, leftPanel, rightPanel;
    public JList<Reservation> list, confirmedList;
    public JButton submitButton, saveButton;
    JLabel methodLabel, priceLabel;
    JTextField methodInput, priceInput;
    TitledBorder listBorder;
    ManageData dataManager = ManageData.getInstance();;
    DefaultListModel<Reservation> listModel, confirmedListModel;

    public ReservationsController() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        // Left Side panel
        listModel = new DefaultListModel<Reservation>();
        listBorder = new TitledBorder(null, "All Reservations");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        list = new JList<Reservation>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(listBorder);
        list.addListSelectionListener(this);

        if (!AgentFrame.listRes.isEmpty()) {
            AgentFrame.listRes.forEach((c) -> {
                listModel.addElement(c);
            });
        }

        // Center Panel
        methodLabel = new JLabel("Transportation Method:");
        methodInput = new JTextField(20);

        priceLabel = new JLabel("Transportation Price ($USD):");
        priceInput = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        mainPanel.add(methodLabel);
        mainPanel.add(methodInput);
        mainPanel.add(priceLabel);
        mainPanel.add(priceInput);
        mainPanel.add(submitButton);

        // Right Side Panel
        confirmedListModel = new DefaultListModel<Reservation>();
        listBorder = new TitledBorder(null, "Confirmed Reservations");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        confirmedList = new JList<Reservation>(confirmedListModel);
        confirmedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        confirmedList.setBorder(listBorder);
        confirmedList.addListSelectionListener(this);

        if (!AgentFrame.listRes.isEmpty()) {
            AgentFrame.listRes.forEach((c) -> {
                if (c.getConfirmation()) {
                    listModel.addElement(c);
                }
            });
        }

    }

    public JButton getRightPanelControllerSave() {
        return saveButton;
    }

    public void saveData() {
        dataManager.saveData("\\reservations", AgentFrame.listRes, "Error saving Reservations data");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() == submitButton) {

        // // Validation successful, create object.
        // if (list.getSelectedValue() == null) {
        // Reservation trans = new Transportation(methodInput.getText(),
        // Double.parseDouble(priceInput.getText()));

        // AgentFrame.listTrans.add(trans);
        // } else {
        // // Updating the transportation instance's info if user is in edit mode
        // Transportation trans = list.getSelectedValue();
        // trans.setName(methodInput.getText());
        // trans.setPrice(Double.parseDouble(priceInput.getText()));

        // }

        // AgentFrame.panel = new TransportationView().mainPanel;
        // AgentFrame.frame.setContentPane(AgentFrame.panel);
        // AgentFrame.frame.revalidate();
        // AgentFrame.frame.repaint();
        // }

        if (e.getSource() == saveButton) {
            if (AgentFrame.listRes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is nothing to save!");
                return;
            }
            saveData();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub

    }

    // @Override
    // public void valueChanged(ListSelectionEvent e) {
    // // Edit mode enabled
    // if (e.getSource() == list) {
    // if (list.getSelectedValue() == null) {
    // submitButton.setText("Submit");
    // methodInput.setText("");
    // priceInput.setText("");
    // return;
    // }

    // submitButton.setText("Update");
    // methodInput.setText(list.getSelectedValue().getName());
    // priceInput.setText(String.valueOf(list.getSelectedValue().getPrice()));

    // }
    // }
}
