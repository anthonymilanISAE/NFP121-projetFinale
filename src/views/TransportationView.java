package views;

import javax.swing.*;

import controllers.TransportationController;
import models.SimpleViewTemplateMethod;

public class TransportationView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    TransportationController pageController = new TransportationController();

    public TransportationView() {
        mainPanel = super.buildView("Transportation",
                pageController.mainPanel,
                pageController.list,
                pageController.saveButton, false);
    }
}
