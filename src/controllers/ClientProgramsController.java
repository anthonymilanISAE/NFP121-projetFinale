package controllers;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.awt.*;

import models.ManageData;
import models.Program;
import frames.ClientFrame;

public class ClientProgramsController implements ActionListener, ListSelectionListener {
    public JList<Program> programList;
    DefaultListModel<Program> programsListModel;

    JTextField transportation, accomodation, activity;

    public JPanel detailsPanel, pList;
    public JButton reserveButton;

    TitledBorder listBorder;
    ManageData dataManager = ManageData.getInstance();;

    public ClientProgramsController() {
        // Details panel
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1));

        //

        // Reserve button
        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this);

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
        dataManager.saveData("\\programs", ClientFrame.listProg, "Error saving Programs data");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == programList) {
            if (programList.getSelectedValue() == null) {
                // submitButton.setText("Submit");
                // nameInput.setText("");
                // priceInput.setText("");
                // descriptionInput.setText("");
                return;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
