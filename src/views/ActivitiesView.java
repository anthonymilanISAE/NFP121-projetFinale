package views;

import javax.swing.*;
import javax.swing.border.*;

import controllers.ActivitiesController;
import models.SimpleViewTemplateMethod;

public class ActivitiesView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    JPanel pRight, pLeft;
    TitledBorder title;
    Border loweredetched;
    ActivitiesController pageController = new ActivitiesController();

    public ActivitiesView() {
        mainPanel = super.buildView("Activities", pageController.mainPanel,
                pageController.list,
                pageController.saveButton);
        ;

    }

}
