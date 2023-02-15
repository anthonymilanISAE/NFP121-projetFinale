package views;

import javax.swing.*;

import controllers.ClientProgramsController;
import models.SimpleViewTemplateMethod;

public class ClientProgramsView extends SimpleViewTemplateMethod {
    public JPanel mainPanel;
    ClientProgramsController pageController = new ClientProgramsController();

    public ClientProgramsView() {
        mainPanel = super.buildView("Make a reservation", pageController.detailsPanel,
                pageController.programList,
                pageController.reserveButton, true);
    }
}
