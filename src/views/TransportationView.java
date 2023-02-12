package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frames.AgentFrame;
import models.ManageData;
import models.Transportation;

public class TransportationView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pRight, pLeft, pInput;
    JLabel methodLabel, priceLabel;
    JTextField methodInput, priceInput;
    ManageData dataManager = ManageData.getInstance();;

    JButton submitButton, saveButton;
    JList<Transportation> list;
    DefaultListModel<Transportation> listModel;

    public TransportationView() {
        // Page wrapper
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Transportation");
        title.setTitleJustification(TitledBorder.CENTER);

        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));

        // Components
        methodLabel = new JLabel("Transportation Method:");
        methodInput = new JTextField(20);

        priceLabel = new JLabel("Transportation Price ($USD):");
        priceInput = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        // Left side

        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(7, 2));

        pInput = new JPanel();
        pInput.setLayout(new GridLayout(0, 1));

        pInput.add(methodLabel);
        pInput.add(methodInput);
        pInput.add(priceLabel);
        pInput.add(priceInput);
        pInput.add(submitButton);

        pLeft.add(pInput);

        // Right side
        listModel = new DefaultListModel<Transportation>();

        if (!AgentFrame.listTransportations.isEmpty()) {
            AgentFrame.listTransportations.forEach((c) -> {
                listModel.addElement(c);
            });
        }

        list = new JList<Transportation>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        TitledBorder listBorder = new TitledBorder(null, "All Transportation methods");

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
