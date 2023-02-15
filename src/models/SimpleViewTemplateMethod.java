package models;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SimpleViewTemplateMethod {
    public JPanel panel;
    JPanel pRight, pLeft;
    TitledBorder title;
    Border loweredetched;

    public final JPanel buildView(String borderTitle, JPanel leftPanel, JList<?> list, JButton saveButton) {
        buildFoundation();
        buildTitle(borderTitle);
        buildLeftPanel(leftPanel);
        buildRightPanel(list, saveButton);
        buildFinal();

        return panel;
    }

    private void buildFoundation() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        // panel.setBackground(Color.RED);
    }

    private void buildTitle(String borderTitle) {
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, borderTitle);
        title.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(title);
    }

    private void buildLeftPanel(JPanel leftPanel) {
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(4, 1));
        pLeft.add(leftPanel);
    }

    private void buildRightPanel(JList<?> list, JButton saveButton) {
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        pRight.add(new JScrollPane(list), BorderLayout.CENTER);
        pRight.add(saveButton, BorderLayout.SOUTH);
    }

    private void buildFinal() {
        panel.add(pLeft);
        panel.add(pRight);
    }

}
