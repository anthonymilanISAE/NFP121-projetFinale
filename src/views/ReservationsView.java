package views;

import javax.swing.*;

import controllers.ReservationsController;
import models.SimpleViewTemplateMethod;

public class ReservationsView extends SimpleViewTemplateMethod {

    public JPanel mainPanel;
    ReservationsController pageController = new ReservationsController();

    public ReservationsView() {
        mainPanel = buildView("Reservations",
                pageController.mainPanel,
                pageController.list,
                pageController.saveButton, false);
    }
}
