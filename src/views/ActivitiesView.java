package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controllers.ActivitiesController;

public class ActivitiesView {

    public JPanel mainPanel;
    JPanel pRight, pLeft;
    ActivitiesController pageController = new ActivitiesController();

    public ActivitiesView() {
        // Page wrapper
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Activities");
        title.setTitleJustification(TitledBorder.CENTER);

        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));

        // Left side
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(4, 1));
        pLeft.add(pageController.mainPanel);

        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        pRight.add(new JScrollPane(pageController.list), BorderLayout.CENTER);
        pRight.add(pageController.saveButton, BorderLayout.SOUTH);

        // Build entire page
        mainPanel.add(pLeft);
        mainPanel.add(pRight);
    }

}
