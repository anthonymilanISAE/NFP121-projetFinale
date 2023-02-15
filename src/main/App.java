package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import frames.AgentFrame;
import frames.ClientFrame;
import models.*;

public class App implements ActionListener {

    public static JFrame frame;
    public static JButton agentLoginButton, userLoginButton;
    public static AgentFrame agentFrame;
    public static ClientFrame clientFrame;

    public static JPanel panel, buttonPanel;
    public static JLabel userLabel, passwordLabel, errorMessage;
    public static JTextField usernameInput;
    public static JPasswordField passwordInput;

    public static Font panelFont, errorFont;

    FileReaderFactory frFactory = FileReaderFactory.getInstance();

    App() {
        // Initialize Frame
        frame = new JFrame("Welcome to our Tourism Agency");
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 600,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 420);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // Font
        panelFont = new Font("Arial", Font.PLAIN, 24);
        errorFont = new Font("Arial", Font.BOLD, 16);

        // Initialize Form
        userLabel = new JLabel();
        userLabel.setText("Enter Username :");
        userLabel.setFont(panelFont);

        errorMessage = new JLabel();
        errorMessage.setText("Incorrect Credentials!");
        errorMessage.setFont(errorFont);
        errorMessage.setForeground(Color.red);
        errorMessage.setVisible(false);

        usernameInput = new JTextField();
        usernameInput.setFont(panelFont);

        passwordLabel = new JLabel();
        passwordLabel.setText("Enter Password :");
        passwordLabel.setFont(panelFont);

        passwordInput = new JPasswordField();
        passwordInput.setFont(panelFont);

        agentLoginButton = new JButton();
        agentLoginButton.setText("Agent Login");
        agentLoginButton.addActionListener(this);

        userLoginButton = new JButton();
        userLoginButton.setText("Client Login");
        userLoginButton.addActionListener(this);

        // Internal Panel for buttons layout
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(agentLoginButton);
        buttonPanel.add(userLoginButton);

        // Add form to panel
        panel = new JPanel(new GridLayout(0, 1, 0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(170, 350, 200, 350));

        panel.add(userLabel);
        panel.add(usernameInput);
        panel.add(passwordLabel);
        panel.add(passwordInput);
        panel.add(buttonPanel);
        panel.add(errorMessage);

        // Add panel to frame
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

    private void closeTheCurrentFrameAndOpenNew(boolean isAgent) {
        // Close current Frame
        frame.dispose();

        // Open new Frame
        if (isAgent) {
            agentFrame = new AgentFrame();
            agentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            agentFrame.setSize(800, 600);
            agentFrame.setVisible(true);
        } else {
            clientFrame = new ClientFrame();
            clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            clientFrame.setSize(800, 600);
            clientFrame.setVisible(true);
        }
    }

    public void setError() {
        errorMessage.setVisible(true);
        usernameInput.setText("");
        passwordInput.setText("");
    }

    public void readCredentials(String directory, boolean isAgent) {
        try {
            BufferedReader BfReader = frFactory.createFileReader(directory);
            String dataLine = BfReader.readLine();
            String inputValue = usernameInput.getText();
            String passwordValue = new String(passwordInput.getPassword());

            if (!inputValue.equals(dataLine)) {
                setError();
            } else {
                dataLine = BfReader.readLine(); // Going to the next line in credentials file
                if (!passwordValue.equals(dataLine)) {
                    setError();
                } else {
                    closeTheCurrentFrameAndOpenNew(isAgent);
                }
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        String os = System.getProperty("os.name");
        if (o == agentLoginButton) {
            String filePath = os.equals("Mac OS X") ? "src/dummyData/adminCredentials.txt"
                    : "src\\dummyData\\adminCredentials.txt";
            // accessing credentials file
            readCredentials(filePath, true);
        } else {
            String filePath = os.equals("Mac OS X") ? "src/dummyData/clientCredentials.txt"
                    : "src\\dummyData\\clientCredentials.txt";
            readCredentials(filePath, false);
        }

    }

    public static void main(String[] args) {
        new App();
    }
}
