package frames;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

import models.*;
import views.AccomodationView;
import views.ActivitiesView;
import views.ProgramsView;
import views.TransportationView;

public class AgentFrame extends JInternalFrame implements ActionListener {

    public static JFrame frame;
    public static JPanel panel;

    JMenuBar mBar;
    JMenuItem iTra, iAct, iAcc, iProg, iRes;
    JButton saveButton;

    public static ArrayList<Transportation> listTrans = new ArrayList<Transportation>();
    public static ArrayList<Activity> listAct = new ArrayList<Activity>();
    public static ArrayList<Accomodation> listAcc = new ArrayList<Accomodation>();
    public static ArrayList<Program> listProg = new ArrayList<Program>();

    String currentPanel = "";

    public AgentFrame() {
        frame = new JFrame("Agent Dashboard");
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 600,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 420);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        mBar = new JMenuBar();

        iTra = new JMenuItem("Transportation");
        iTra.addActionListener(this);

        iAct = new JMenuItem("Activities");
        iAct.addActionListener(this);

        iAcc = new JMenuItem("Accomodation");
        iAcc.addActionListener(this);

        iProg = new JMenuItem("Programs");
        iProg.addActionListener(this);

        iRes = new JMenuItem("Reservations");
        iRes.addActionListener(this);

        saveButton = new JButton();
        saveButton.setText("Agent Login");
        saveButton.addActionListener(this);

        mBar.add(iTra);
        mBar.add(iAct);
        mBar.add(iAcc);
        mBar.add(iProg);
        mBar.add(iRes);

        panel.add(saveButton);
        frame.setJMenuBar(mBar);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Stop the user from navigating to same panel
        if (e.getActionCommand().toString().equals(currentPanel)) {
            JOptionPane.showMessageDialog(null, "You are already in the \"" +
                    currentPanel + "\" Section");
            return;
        }
        Object o = e.getSource();
        currentPanel = e.getActionCommand().toString();

        if (o == iTra) {
            changePanel(new TransportationView().mainPanel);
        }
        if (o == iAct) {
            changePanel(new ActivitiesView().mainPanel);
        }
        if (o == iAcc) {
            changePanel(new AccomodationView().mainPanel);
        }
        if (o == iProg) {
            changePanel(new ProgramsView().mainPanel);
        }

    }

    public void changePanel(JPanel newPanel) {
        if (panel.isShowing()) {
            frame.remove(panel);
        }
        panel = newPanel;
        frame.setContentPane(panel);
        frame.pack();
    }

    // * Function that loads data from files and saves them in the object arrays
    @SuppressWarnings("unchecked")
    public void loadData() {
        File directory = new File("data");
        if (directory.isDirectory() && directory.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;
            // Load Transportation Data
            try {
                File fileEns = new File(directory.getName() + "\\transportation");
                if (fileEns.exists()) {
                    fis = new FileInputStream(fileEns);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Transportation> tempList = (ArrayList<Transportation>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listTrans = tempList;
                        Transportation.setCounter(listTrans.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error loading Transportation data!");
            }

            // Load Activity Data
            try {
                File fileEns = new File(directory.getName() + "\\activities");
                if (fileEns.exists()) {
                    fis = new FileInputStream(fileEns);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Activity> tempList = (ArrayList<Activity>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listAct = tempList;
                        Activity.setCounter(listAct.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error loading Activities data");
            }
            // Load Accomodations Data
            try {
                File fileEns = new File(directory.getName() + "\\accomodations");

                if (fileEns.exists()) {
                    fis = new FileInputStream(fileEns);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Accomodation> tempList = (ArrayList<Accomodation>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listAcc = tempList;
                        Accomodation.setCounter(listAcc.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error loading Accomodations data!");
            }
            // Load Programs Data
            try {
                File fileEns = new File(directory.getName() + "\\programs");
                if (fileEns.exists()) {
                    fis = new FileInputStream(fileEns);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Program> tempList = (ArrayList<Program>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listProg = tempList;
                        Program.setCounter(listProg.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading Programs data!");
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    // * Run the app
    public static void main(String[] args) {
        new AgentFrame();
    }
}
