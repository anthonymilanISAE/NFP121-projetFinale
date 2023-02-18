package views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frames.AgentFrame;
import models.ManageData;
import models.Reservation;
import views.ReservationsView;

public class ReservationsView implements ActionListener, ListSelectionListener {
    public JPanel mainPanel, leftPanel, middlePanel, rightPanel, buttonPanel;
    public JList<Reservation> list, confirmedList;
    public JButton saveButton, confirmButton, cancelButton;
    TitledBorder listBorder;
    ManageData dataManager = ManageData.getInstance();;
    DefaultListModel<Reservation> listModel, confirmedListModel;

    public ReservationsView() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(0, 1));

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

        leftPanel.add(list);

        confirmButton = new JButton("Confirm Reservation");
        confirmButton.addActionListener(this);
        confirmButton.setEnabled(false);

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.addActionListener(this);
        cancelButton.setEnabled(false);

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);

        saveButton = new JButton("Save changes");
        saveButton.addActionListener(this);

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(3, 1));
        middlePanel.add(buttonPanel);
        middlePanel.add(saveButton);

        // Right Side Panel
        confirmedListModel = new DefaultListModel<Reservation>();
        listBorder = new TitledBorder(null, "Confirmed Reservations");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        confirmedList = new JList<Reservation>(confirmedListModel);
        confirmedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        confirmedList.setBorder(listBorder);
        confirmedList.addListSelectionListener(this);

        if (!AgentFrame.listConfRes.isEmpty()) {
            AgentFrame.listConfRes.forEach((c) -> {
                confirmedListModel.addElement(c);
            });
        }

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(0, 1));
        rightPanel.add(confirmedList);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3));
        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(rightPanel);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list) {
            confirmButton.setEnabled(true);
            cancelButton.setEnabled(false);
        }

        if (e.getSource() == confirmedList) {
            confirmButton.setEnabled(false);
            cancelButton.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            Reservation selectedRes = list.getSelectedValue();
            if (selectedRes.getPrice() > selectedRes.getUser().getBalance()) {
                JOptionPane.showMessageDialog(null, "User cannot afford to make this reservation");
                return;
            }

            Reservation confirmedRes = new Reservation(selectedRes.getProgram(), selectedRes.getUser(), true,
                    selectedRes.getNbReservations());

            AgentFrame.listConfRes.add(confirmedRes);
            AgentFrame.listRes.remove(selectedRes);
            selectedRes.getUser().reduceBalance(selectedRes.getPrice());
        }

        if (e.getSource() == cancelButton) {
            Reservation selectedRes = confirmedList.getSelectedValue();
            Reservation canceledRes = new Reservation(selectedRes.getProgram(), selectedRes.getUser(), false,
                    selectedRes.getNbReservations());
            AgentFrame.listRes.add(canceledRes);
            AgentFrame.listConfRes.remove(canceledRes);
            selectedRes.getUser().addBalance(selectedRes.getPrice());

        }

        if (e.getSource() == saveButton) {
            saveData();
        }

        AgentFrame.panel = new ReservationsView().mainPanel;
        AgentFrame.frame.setContentPane(AgentFrame.panel);
        AgentFrame.frame.revalidate();
        AgentFrame.frame.repaint();
    }

    public void saveData() {
        dataManager.saveData("\\reservations", AgentFrame.listRes, "Error saving Reservations");
        dataManager.saveData("\\confirmedReservations", AgentFrame.listConfRes, "Error saving Confirmed Reservations");
    }
}
