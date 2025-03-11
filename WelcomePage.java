import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame implements ActionListener {

    private final JButton proceedButton;

    public WelcomePage() {
        // Setting up the frame
        JFrame frame = new JFrame("Welcome to Student Management System");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Student Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(25, 100, 200));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        // Proceed button
        proceedButton = new JButton("Enter");
        proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
        proceedButton.setBackground(new Color(100, 180, 255));
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFocusPainted(false); // Remove the focus border on the button
        proceedButton.setPreferredSize(new Dimension(200, 40));
        proceedButton.addActionListener(this);

        // Creating a panel for the button with padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(proceedButton);

        // Adding components to the main panel
        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);


        frame.add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proceedButton) {
            // Dispose of the current welcome page
            this.dispose();

            // Open the main AppGUI
            AppGUI mainApp = new AppGUI();
            mainApp.setVisible(true);
        }
    }

    public static void main(String[] args) {
        // Launch the welcome page
        SwingUtilities.invokeLater(WelcomePage::new);
    }
}
