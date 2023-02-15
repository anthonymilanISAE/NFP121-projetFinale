package controllers;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;
import java.awt.*;

import models.ManageData;
import models.Program;
import models.Reservation;
import models.ReservationsObservable;
import models.ReservationsObserver;
import models.User;
import views.ClientProgramsView;
import frames.AgentFrame;
import frames.ClientFrame;

public class ClientProgramsController implements ActionListener, ListSelectionListener {
    public JList<Program> programList;
    DefaultListModel<Program> programsListModel;
    Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
    Date myDate = myCalendar.getTime();

    JLabel transportationTitle, accomodationTitle, accomodationSubtitle, activityTitle, activitySubtitle,
            nbOfReservationsLabel, totalPriceTitle, totalPriceSubtitle;
    JTextField nbOfReservations;
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    User user = User.getInstance("Anthony", "Milan", 6500, myDate);

    public JPanel detailsPanel, pList, reservationsPanel, pricePanel;
    public JButton reserveButton;

    TitledBorder title;
    Border loweredetched;
    TitledBorder listBorder;
    ManageData dataManager = ManageData.getInstance();;
    Font titleFont = new Font("Arial", Font.BOLD, 16);
    Font subtitleFont = new Font("Arial", Font.PLAIN, 14);
    ReservationsObservable reservObservable = new ReservationsObservable(1);
    ReservationsObserver priceObs = new ReservationsObserver();

    public ClientProgramsController() {
        // Details panel
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1));

        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, "View Program Details");
        title.setTitleJustification(TitledBorder.CENTER);
        detailsPanel.setBorder(title);

        // Details text
        transportationTitle = new JLabel();
        transportationTitle.setFont(titleFont);
        accomodationTitle = new JLabel();
        accomodationTitle.setFont(titleFont);
        accomodationSubtitle = new JLabel();
        accomodationSubtitle.setFont(subtitleFont);
        activityTitle = new JLabel();
        activityTitle.setFont(titleFont);
        activitySubtitle = new JLabel();
        activitySubtitle.setFont(subtitleFont);

        // Reservations text
        reservObservable.addObserver(priceObs);
        reservationsPanel = new JPanel();
        reservationsPanel.setLayout(new GridLayout(0, 2));
        nbOfReservationsLabel = new JLabel("Select number of reservations: ");
        nbOfReservations = reservObservable.createField();
        Document document = nbOfReservations.getDocument();
        document.addDocumentListener(new DocumentListener() {
            public void onChange() {
                try {
                    if (Integer.parseInt(nbOfReservations.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Please enter number bigger than 0", "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        return;

                    } else {
                        reservObservable.setValue(Integer.parseInt(nbOfReservations.getText()));
                        double totalPrice = calculateValue(priceObs.getPrice());
                        totalPriceSubtitle.setText(String.valueOf(totalPrice));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error: Please enter number bigger than 0", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;

                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange();
            }
        });
        reservationsPanel.add(nbOfReservationsLabel);
        reservationsPanel.add(nbOfReservations);

        // Price text
        pricePanel = new JPanel();
        pricePanel.setLayout(new GridLayout(0, 2));
        totalPriceTitle = new JLabel("Total Price: ");
        totalPriceTitle.setFont(titleFont);
        totalPriceSubtitle = new JLabel();
        totalPriceSubtitle.setFont(subtitleFont);
        pricePanel.add(totalPriceTitle);
        pricePanel.add(totalPriceSubtitle);

        // Reserve button
        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this);

        detailsPanel.add(transportationTitle);
        detailsPanel.add(accomodationTitle);
        detailsPanel.add(accomodationSubtitle);
        detailsPanel.add(activityTitle);
        detailsPanel.add(activitySubtitle);
        detailsPanel.add(reservationsPanel);
        detailsPanel.add(pricePanel);

        // Programs List
        programsListModel = new DefaultListModel<Program>();
        listBorder = new TitledBorder(null, "All Programs");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        programList = new JList<Program>(programsListModel);
        programList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        programList.addListSelectionListener(this);
        programList.setBorder(listBorder);

        if (!ClientFrame.listProg.isEmpty()) {
            ClientFrame.listProg.forEach((c) -> {
                programsListModel.addElement(c);
            });
        }

        pList = new JPanel();
        pList.setLayout(new BorderLayout());
        pList.add(new JScrollPane(programList), BorderLayout.CENTER);
    }

    public void saveData() {
        Reservation res = new Reservation(programList.getSelectedValue(),
                user, false, Integer.parseInt(nbOfReservations.getText()));

        AgentFrame.listRes.add(res);
        dataManager.saveData("\\reservations", AgentFrame.listRes, "Error creating your Reservation");
    }

    public double calculateValue(float price) {
        return (programList.getSelectedValue().getTransportation().getPrice()
                + programList.getSelectedValue().getAccomodation().getPrice()
                + programList.getSelectedValue().getActivity().getPrice()) * price;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == programList) {
            if (programList.getSelectedValue() == null) {
                return;
            }
            transportationTitle.setText(programList.getSelectedValue().getTransportation().toString());
            accomodationTitle.setText(programList.getSelectedValue().getAccomodation().toString());
            accomodationSubtitle.setText(programList.getSelectedValue().getAccomodation().getLocation());
            activityTitle.setText(programList.getSelectedValue().getActivity().toString());
            activitySubtitle.setText(programList.getSelectedValue().getActivity().getDescription());

            double totalPrice = calculateValue(Integer.parseInt(nbOfReservations.getText()));
            totalPriceSubtitle.setText(String.valueOf(totalPrice));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserveButton) {
            if (programList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select a Program", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Integer.parseInt(nbOfReservations.getText()) <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Please enter valid number of registrations", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Float.parseFloat(totalPriceSubtitle.getText()) > user.getBalance()) {
                JOptionPane.showMessageDialog(null,
                        "You cannot afford to make this reservation.", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            saveData();

            ClientFrame.panel = new ClientProgramsView().mainPanel;
            ClientFrame.frame.setContentPane(ClientFrame.panel);
            ClientFrame.frame.revalidate();
            ClientFrame.frame.repaint();

        }
    }
}
