package views;

import javax.swing.*;
import javax.swing.border.*;

import controllers.AccomodationsController;
import models.SimpleViewTemplateMethod;

public class AccomodationView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    JPanel pRight, pLeft;
    TitledBorder title;
    Border loweredetched;
    AccomodationsController pageController = new AccomodationsController();

    public AccomodationView() {
        mainPanel = super.buildView("Accomodations", pageController.mainPanel,
                pageController.list,
                pageController.saveButton);
        ;

    }

}
