package views;

import javax.swing.*;

import controllers.ActivitiesController;
import models.SimpleViewTemplateMethod;

public class ActivitiesView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    ActivitiesController pageController = new ActivitiesController();

    public ActivitiesView() {
        mainPanel = super.buildView("Activities", pageController.mainPanel,
                pageController.list,
                pageController.saveButton);
    }
}
