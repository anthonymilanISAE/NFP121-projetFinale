package controllers;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Activity;
import models.ManageData;
import frames.AgentFrame;
import views.ActivitiesView;

public class ActivitiesController implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    public JButton submitButton, saveButton;
    public JList<Activity> list;
    JLabel nameLabel, priceLabel, descriptionLabel;
    JTextField nameInput, priceInput, descriptionInput;
    ManageData dataManager = ManageData.getInstance();;
    TitledBorder listBorder;

    DefaultListModel<Activity> listModel;

    public ActivitiesController() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        nameLabel = new JLabel("Activity:");
        nameInput = new JTextField(20);

        priceLabel = new JLabel("Activity Price ($USD):");
        priceInput = new JTextField(20);

        descriptionLabel = new JLabel("Activity description:");
        descriptionInput = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        mainPanel.add(nameLabel);
        mainPanel.add(nameInput);
        mainPanel.add(priceLabel);
        mainPanel.add(priceInput);
        mainPanel.add(descriptionLabel);
        mainPanel.add(descriptionInput);
        mainPanel.add(submitButton);

        // Right side
        listModel = new DefaultListModel<Activity>();
        listBorder = new TitledBorder(null, "All Activities");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        list = new JList<Activity>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setBorder(listBorder);

        if (!AgentFrame.listAct.isEmpty()) {
            AgentFrame.listAct.forEach((c) -> {
                listModel.addElement(c);
            });
        }

    }

    public void saveData() {
        dataManager.saveData("\\activities", AgentFrame.listAct, "Error saving Activities data");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            // Validating form
            if (nameInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Activitiy name");
                return;
            }

            if (priceInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Activitiy price");
                return;
            }

            if (descriptionInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Activity description");
                return;
            }

            if (priceInput.getText().matches(".*[a-z].*")) {
                JOptionPane.showMessageDialog(null, "Price must be a number");
                return;
            }

            // Validation successful, create object.
            if (list.getSelectedValue() == null) {
                Activity act = new Activity(nameInput.getText(),
                        Double.parseDouble(priceInput.getText()), descriptionInput.getText());

                AgentFrame.listAct.add(act);
            } else {
                // Updating the transportation instance's info if user is in edit mode
                Activity act = list.getSelectedValue();
                act.setName(nameInput.getText());
                act.setPrice(Double.parseDouble(priceInput.getText()));
                act.setName(descriptionInput.getText());

            }

            AgentFrame.panel = new ActivitiesView().mainPanel;
            AgentFrame.frame.setContentPane(AgentFrame.panel);
            AgentFrame.frame.revalidate();
            AgentFrame.frame.repaint();
        }

        if (e.getSource() == saveButton) {

            if (AgentFrame.listAct.isEmpty()) {
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
                descriptionInput.setText("");
                return;
            }

            submitButton.setText("Update");
            nameInput.setText(list.getSelectedValue().getName());
            priceInput.setText(String.valueOf(list.getSelectedValue().getPrice()));
            descriptionInput.setText(list.getSelectedValue().getDescription());

        }
    }

}
