package controllers;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frames.AgentFrame;
import models.ManageData;
import models.Transportation;
import views.TransportationView;

public class TransportationController implements ActionListener, ListSelectionListener {
    JPanel panel;
    JLabel methodLabel, priceLabel;
    JTextField methodInput, priceInput;
    JButton submitButton, saveButton;
    TitledBorder listBorder;
    ManageData dataManager = ManageData.getInstance();;
    JList<Transportation> list;
    DefaultListModel<Transportation> listModel;

    public TransportationController() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        methodLabel = new JLabel("Transportation Method:");
        methodInput = new JTextField(20);

        priceLabel = new JLabel("Transportation Price ($USD):");
        priceInput = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        // Left side Panel
        panel.add(methodLabel);
        panel.add(methodInput);
        panel.add(priceLabel);
        panel.add(priceInput);
        panel.add(submitButton);

        // Right Side panel
        listModel = new DefaultListModel<Transportation>();
        listBorder = new TitledBorder(null, "All Transportation methods");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        list = new JList<Transportation>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(listBorder);
        list.addListSelectionListener(this);

        if (!AgentFrame.listTransportations.isEmpty()) {
            AgentFrame.listTransportations.forEach((c) -> {
                listModel.addElement(c);
            });
        }

    }

    public JPanel getLeftPanelController() {
        return panel;
    }

    public JList<Transportation> getRightPanelControllerList() {
        return list;
    }

    public JButton getRightPanelControllerSave() {
        return saveButton;
    }

    public void saveData() {
        dataManager.saveData("\\transportation", AgentFrame.listTransportations, "Error saving Transportation data");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            // Validating form
            if (methodInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Transportation method name");
                return;
            }

            if (priceInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Transportation method price");
                return;
            }

            if (priceInput.getText().matches(".*[a-z].*")) {
                JOptionPane.showMessageDialog(null, "Price must be a number");
                return;
            }

            // Validation successful, create object.
            if (list.getSelectedValue() == null) {
                Transportation trans = new Transportation(methodInput.getText(),
                        Double.parseDouble(priceInput.getText()));

                AgentFrame.listTransportations.add(trans);
            } else {
                // Updating the transportation instance's info if user is in edit mode
                Transportation trans = list.getSelectedValue();
                trans.setName(methodInput.getText());
                trans.setPrice(Double.parseDouble(priceInput.getText()));

            }

            AgentFrame.panel = new TransportationView().mainPanel;
            AgentFrame.frame.setContentPane(AgentFrame.panel);
            AgentFrame.frame.revalidate();
            AgentFrame.frame.repaint();
        }

        if (e.getSource() == saveButton) {

            if (AgentFrame.listTransportations.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is nothing to save!");
                return;
            }
            saveData();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Edit mode enabled
        if (e.getSource() == list) {
            if (list.getSelectedValue() == null) {
                submitButton.setText("Submit");
                methodInput.setText("");
                priceInput.setText("");
                return;
            }

            submitButton.setText("Update");
            methodInput.setText(list.getSelectedValue().getName());
            priceInput.setText(String.valueOf(list.getSelectedValue().getPrice()));

        }
    }
}
