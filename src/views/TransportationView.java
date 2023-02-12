package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controllers.TransportationController;

public class TransportationView {

    public JPanel mainPanel;
    Border loweredetched;
    TitledBorder title;
    JPanel pRight, pLeft;
    TransportationController pageController = new TransportationController();

    public TransportationView() {
        // Page wrapper
        mainPanel = new JPanel();
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, "Transportation");
        title.setTitleJustification(TitledBorder.CENTER);

        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));

        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(7, 2));
        pLeft.add(pageController.panel);

        // Right side
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        pRight.add(new JScrollPane(pageController.list), BorderLayout.CENTER);
        pRight.add(pageController.saveButton, BorderLayout.SOUTH);

        // Build entire page
        mainPanel.add(pLeft);
        mainPanel.add(pRight);
    }

}
