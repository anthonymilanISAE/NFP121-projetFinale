package controllers;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Accomodation;
import models.ManageData;
import models.StarRater;
import frames.AgentFrame;
import views.AccomodationView;

public class AccomodationsController implements ActionListener, ListSelectionListener {

    public JPanel mainPanel, submitPanel;
    public JButton submitButton, saveButton;
    public JList<Accomodation> list;
    JLabel nameLabel, priceLabel, starLabel, locationLabel;
    JTextField nameInput, priceInput, descriptionInput, locationInput;
    ManageData dataManager = ManageData.getInstance();;
    TitledBorder listBorder;
    StarRater starRater;

    DefaultListModel<Accomodation> listModel;

    public AccomodationsController() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));
        submitPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        nameLabel = new JLabel("Accomodation:");
        nameInput = new JTextField(20);

        priceLabel = new JLabel("Accomodation Price ($USD / per night):");
        priceInput = new JTextField(20);

        starLabel = new JLabel("Accomodation Number of Stars:");
        starRater = new StarRater(5);

        locationLabel = new JLabel("Accomodation Location:");
        locationInput = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        mainPanel.add(nameLabel);
        mainPanel.add(nameInput);
        mainPanel.add(priceLabel);
        mainPanel.add(priceInput);
        mainPanel.add(starLabel);
        mainPanel.add(starRater);
        mainPanel.add(locationLabel);
        mainPanel.add(locationInput);
        mainPanel.add(submitButton);

        // Right side
        listModel = new DefaultListModel<Accomodation>();
        listBorder = new TitledBorder(null, "All Accomodations");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        list = new JList<Accomodation>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setBorder(listBorder);

        if (!AgentFrame.listAcc.isEmpty()) {
            AgentFrame.listAcc.forEach((c) -> {
                listModel.addElement(c);
            });
        }

    }

    public void saveData() {
        dataManager.saveData("\\accomodations", AgentFrame.listAcc, "Error saving Accomodations data");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            // Validating form
            if (nameInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Accomodation name");
                return;
            }

            if (priceInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Accomodation price");
                return;
            }

            if (locationInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Accomodation location");
                return;
            }

            if (priceInput.getText().matches(".*[a-z].*")) {
                JOptionPane.showMessageDialog(null, "Price must be a number");
                return;
            }

            // Validation successful, create object.
            if (list.getSelectedValue() == null) {
                Accomodation acc = new Accomodation(nameInput.getText(),
                        Double.parseDouble(priceInput.getText()), starRater.getSelection(), locationInput.getText());

                AgentFrame.listAcc.add(acc);
            } else {
                // Updating the transportation instance's info if user is in edit mode
                Accomodation acc = list.getSelectedValue();
                acc.setName(nameInput.getText());
                acc.setPrice(Double.parseDouble(priceInput.getText()));
                acc.setStars(starRater.getSelection());
                acc.setLocation(locationInput.getText());

            }

            AgentFrame.panel = new AccomodationView().mainPanel;
            AgentFrame.frame.setContentPane(AgentFrame.panel);
            AgentFrame.frame.revalidate();
            AgentFrame.frame.repaint();
        }

        if (e.getSource() == saveButton) {
            if (AgentFrame.listAcc.isEmpty()) {
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
                nameInput.setText("");
                priceInput.setText("");
                starRater.setSelection(0);
                return;
            }
            submitButton.setText("Update");
            nameInput.setText(list.getSelectedValue().getName());
            priceInput.setText(String.valueOf(list.getSelectedValue().getPrice()));
            starRater.setSelection(list.getSelectedValue().getStars());
            locationInput.setText(list.getSelectedValue().getLocation());
        }
    }

}
