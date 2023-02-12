package views;

import javax.swing.*;
import javax.swing.border.*;

import controllers.TransportationController;
import models.SimpleViewTemplateMethod;

public class TransportationView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    Border loweredetched;
    TitledBorder title;
    JPanel pRight, pLeft;
    TransportationController pageController = new TransportationController();

    public TransportationView() {
        mainPanel = super.buildView("Transportation",
                pageController.mainPanel,
                pageController.list,
                pageController.saveButton);

    }

}
