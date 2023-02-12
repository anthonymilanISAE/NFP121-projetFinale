package views;

import javax.swing.*;
import javax.swing.border.*;

import controllers.TransportationController;
import models.SimpleViewFactory;

public class TransportationView {

    public JPanel mainPanel;
    Border loweredetched;
    TitledBorder title;
    JPanel pRight, pLeft;
    TransportationController pageController = new TransportationController();

    public TransportationView() {
        mainPanel = new JPanel();

        SimpleViewFactory simpleViewFactory = new SimpleViewFactory("Transportation", pageController.mainPanel,
                pageController.list,
                pageController.saveButton);

        mainPanel = simpleViewFactory.getView();
    }

}
