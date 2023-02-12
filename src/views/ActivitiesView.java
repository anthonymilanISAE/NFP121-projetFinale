package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frames.AgentFrame;
import models.ManageData;
import models.Activity;

public class ActivitiesView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pRight, pLeft, pInput;
    JLabel nameLabel, priceLabel, descriptionLabel;
    JTextField nameInput, priceInput, descriptionInput;
    ManageData dataManager = ManageData.getInstance();;

    JButton submitButton, saveButton;
    JList<Activity> list;
    DefaultListModel<Activity> listModel;

    public ActivitiesView() {
        // Page wrapper
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Activities");
        title.setTitleJustification(TitledBorder.CENTER);

        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));

        // Components
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

        // Left side
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(4, 1));

        pInput = new JPanel();
        pInput.setLayout(new GridLayout(0, 1));

        pInput.add(nameLabel);
        pInput.add(nameInput);
        pInput.add(priceLabel);
        pInput.add(priceInput);
        pInput.add(descriptionLabel);
        pInput.add(descriptionInput);
        pInput.add(submitButton);

        pLeft.add(pInput);

        // Right side
        listModel = new DefaultListModel<Activity>();

        if (!AgentFrame.listAct.isEmpty()) {
            AgentFrame.listAct.forEach((c) -> {
                listModel.addElement(c);
            });
        }

        list = new JList<Activity>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        TitledBorder listBorder = new TitledBorder(null, "All Activities");

        listBorder.setTitleJustification(TitledBorder.CENTER);
        list.setBorder(listBorder);

        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        pRight.add(new JScrollPane(list), BorderLayout.CENTER);
        pRight.add(saveButton, BorderLayout.SOUTH);

        // Build entire page
        mainPanel.add(pLeft);
        mainPanel.add(pRight);
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
