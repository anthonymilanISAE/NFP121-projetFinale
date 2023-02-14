package controllers;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.awt.*;

import models.Accomodation;
import models.Activity;
import models.ManageData;
import models.Program;
import models.Transportation;
import views.ProgramsView;
import frames.AgentFrame;

public class ProgramsController implements ActionListener, ListSelectionListener {
    public JList<Accomodation> accomodationList;
    DefaultListModel<Accomodation> accomodationListModel;

    public JList<Activity> activitiesList;
    DefaultListModel<Activity> activitiesListModel;

    public JList<Transportation> transportationsList;
    DefaultListModel<Transportation> transportationsListModel;

    public JList<Program> programList;
    DefaultListModel<Program> programsListModel;

    public JPanel buttonPanel, pRight;
    JButton addButton, saveButton;

    TitledBorder listBorder;
    ManageData dataManager = ManageData.getInstance();;

    public ProgramsController() {
        // Transportations List
        transportationsListModel = new DefaultListModel<Transportation>();
        listBorder = new TitledBorder(null, "All Transportations");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        transportationsList = new JList<Transportation>(transportationsListModel);
        transportationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transportationsList.addListSelectionListener(this);
        transportationsList.setBorder(listBorder);

        if (!AgentFrame.listTrans.isEmpty()) {
            AgentFrame.listTrans.forEach((c) -> {
                transportationsListModel.addElement(c);
            });
        }

        // Activities List
        activitiesListModel = new DefaultListModel<Activity>();
        listBorder = new TitledBorder(null, "All Activities");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        activitiesList = new JList<Activity>(activitiesListModel);
        activitiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        activitiesList.addListSelectionListener(this);
        activitiesList.setBorder(listBorder);

        if (!AgentFrame.listAct.isEmpty()) {
            AgentFrame.listAct.forEach((c) -> {
                activitiesListModel.addElement(c);
            });
        }

        // Accomodations List
        accomodationListModel = new DefaultListModel<Accomodation>();
        listBorder = new TitledBorder(null, "All Accomodations");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        accomodationList = new JList<Accomodation>(accomodationListModel);
        accomodationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accomodationList.addListSelectionListener(this);
        accomodationList.setBorder(listBorder);

        if (!AgentFrame.listAcc.isEmpty()) {
            AgentFrame.listAcc.forEach((c) -> {
                accomodationListModel.addElement(c);
            });
        }

        // Add button
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(addButton);

        // Programs List
        programsListModel = new DefaultListModel<Program>();
        listBorder = new TitledBorder(null, "All Programs");
        listBorder.setTitleJustification(TitledBorder.CENTER);

        programList = new JList<Program>(programsListModel);
        programList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        programList.addListSelectionListener(this);
        programList.setBorder(listBorder);

        if (!AgentFrame.listProg.isEmpty()) {
            AgentFrame.listProg.forEach((c) -> {
                programsListModel.addElement(c);
            });

        }

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        pRight = new JPanel();
        pRight.setLayout(new BorderLayout());
        pRight.add(new JScrollPane(programList), BorderLayout.CENTER);
        pRight.add(saveButton, BorderLayout.SOUTH);

    }

    public void saveData() {
        dataManager.saveData("\\programs", AgentFrame.listProg, "Error saving Programs data");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == programList) {
            if (programList.getSelectedValue() == null) {
                addButton.setText("Submit");
                return;
            }

            try {
                addButton.setText("Update");

                activitiesList.setSelectedValue(programList.getSelectedValue().getActivity(), true);
                accomodationList.setSelectedValue(programList.getSelectedValue().getAccomodation(), true);
                transportationsList.setSelectedValue(programList.getSelectedValue().getTransportation(), true);
            } catch (Error error) {
                System.out.println(error);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (accomodationList.getSelectedValue() == null || activitiesList.getSelectedValue() == null
                    || transportationsList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Please select one of each field above!");
                return;
            }

            if (programList.getSelectedValue() == null) {
                Program prog = new Program(activitiesList.getSelectedValue(),
                        transportationsList.getSelectedValue(),
                        accomodationList.getSelectedValue());
                AgentFrame.listProg.add(prog);
            } else {
                Program prog = programList.getSelectedValue();
                prog.setActivity(activitiesList.getSelectedValue());
                prog.setAccomodation(accomodationList.getSelectedValue());
                prog.setTransportation(transportationsList.getSelectedValue());
            }
        }

        if (e.getSource() == saveButton) {
            if (AgentFrame.listProg.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is nothing to save!");
                return;
            }
            saveData();
            return;
        }

        AgentFrame.panel = new ProgramsView().mainPanel;
        AgentFrame.frame.setContentPane(AgentFrame.panel);
        AgentFrame.frame.revalidate();
        AgentFrame.frame.repaint();

    }
}
