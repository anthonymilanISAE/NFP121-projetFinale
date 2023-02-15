package frames;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

import models.*;
import views.ClientProgramsView;

public class ClientFrame extends JInternalFrame implements ActionListener {

    public static JFrame frame;
    public static JPanel panel;

    JMenuBar mBar;
    JMenuItem iProg, iProf;

    public static ArrayList<Program> listProg = new ArrayList<Program>();

    String currentPanel = "";

    public ClientFrame() {
        frame = new JFrame("User Dashboard");
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 600,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 420);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        mBar = new JMenuBar();

        iProg = new JMenuItem("Programs");
        iProg.addActionListener(this);

        iProf = new JMenuItem("Profile");
        iProf.addActionListener(this);

        mBar.add(iProg);
        mBar.add(iProf);

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

        if (o == iProg) {
            changePanel(new ClientProgramsView().mainPanel);
        }
        // if (o == iProf) {
        // changePanel(new ProfileView().mainPanel);
        // }

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
        new ClientFrame();
    }
}
