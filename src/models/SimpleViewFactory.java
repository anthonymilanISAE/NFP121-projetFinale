package models;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SimpleViewFactory {
    public JPanel panel;
    JPanel pRight, pLeft;
    TitledBorder title;
    Border loweredetched;

    public SimpleViewFactory(String borderTitle, JPanel leftPanel, JList<?> list, JButton saveButton) {
        panel = new JPanel();
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, borderTitle);
        title.setTitleJustification(TitledBorder.CENTER);

        panel.setBorder(title);
        panel.setLayout(new GridLayout(1, 2));

        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(4, 1));
        pLeft.add(leftPanel);

        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        pRight.add(new JScrollPane(list), BorderLayout.CENTER);
        pRight.add(saveButton, BorderLayout.SOUTH);

        // Build entire page
        panel.add(pLeft);
        panel.add(pRight);
    }

    public JPanel getView() {
        return panel;
    }

}
