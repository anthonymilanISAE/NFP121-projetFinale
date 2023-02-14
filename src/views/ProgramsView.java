package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controllers.ProgramsController;

public class ProgramsView {
    public JPanel mainPanel, transportPanel, activitiesPanel, accomodationsPanel, pLeft, pRight;
    JLabel instructionLabel;
    Border loweredetched;
    TitledBorder title;
    ProgramsController programsController = new ProgramsController();

    public ProgramsView() {
        // Creating Wrapper
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, "Create a Program");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);

        // Left Side All three panels
        transportPanel = new JPanel();
        transportPanel.setLayout(new GridLayout(0, 1));
        transportPanel.add(new JScrollPane(programsController.transportationsList), BorderLayout.WEST);

        accomodationsPanel = new JPanel();
        accomodationsPanel.setLayout(new GridLayout(0, 1));
        accomodationsPanel.add(new JScrollPane(programsController.accomodationList), BorderLayout.WEST);

        activitiesPanel = new JPanel();
        activitiesPanel.setLayout(new GridLayout(0, 1));
        activitiesPanel.add(new JScrollPane(programsController.activitiesList), BorderLayout.WEST);

        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(4, 1));
        pLeft.add(transportPanel);
        pLeft.add(activitiesPanel);
        pLeft.add(accomodationsPanel);
        pLeft.add(programsController.buttonPanel);

        mainPanel.add(pLeft);
        mainPanel.add(programsController.pRight);
    }
}
