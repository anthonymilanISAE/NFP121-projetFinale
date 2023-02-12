package views;

import javax.swing.*;
import javax.swing.border.*;

import controllers.AccomodationsController;
import models.SimpleViewFactory;

public class AccomodationView {

    public JPanel mainPanel;
    JPanel pRight, pLeft;
    TitledBorder title;
    Border loweredetched;
    AccomodationsController pageController = new AccomodationsController();

    public AccomodationView() {
        mainPanel = new JPanel();

        SimpleViewFactory simpleViewFactory = new SimpleViewFactory("Accomodations", pageController.mainPanel,
                pageController.list,
                pageController.saveButton);

        mainPanel = simpleViewFactory.getView();
    }

}
