package views;

import javax.swing.*;
import javax.swing.border.*;

import controllers.ActivitiesController;
import models.SimpleViewFactory;

public class ActivitiesView {

    public JPanel mainPanel;
    JPanel pRight, pLeft;
    TitledBorder title;
    Border loweredetched;
    ActivitiesController pageController = new ActivitiesController();

    public ActivitiesView() {
        mainPanel = new JPanel();

        SimpleViewFactory simpleViewFactory = new SimpleViewFactory("Activities", pageController.mainPanel,
                pageController.list,
                pageController.saveButton);

        mainPanel = simpleViewFactory.getView();
    }

}
