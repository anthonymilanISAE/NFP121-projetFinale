package views;

import javax.swing.*;

import controllers.AccomodationsController;
import models.SimpleViewTemplateMethod;

public class AccomodationView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    AccomodationsController pageController = new AccomodationsController();

    public AccomodationView() {
        mainPanel = super.buildView("Accomodations", pageController.mainPanel,
                pageController.list,
                pageController.saveButton, false);
    }
}
