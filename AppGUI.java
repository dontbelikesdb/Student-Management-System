import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AppGUI extends JFrame implements ActionListener {

    private final JLabel studentIdLabel, firstNameLabel, lastNameLabel, majorLabel, phoneLabel, gpaLabel, dobLabel;
    private final JTextField studentIdField, firstNameField, lastNameField, majorField, phoneField, gpaField, dobField;
    private final JButton addButton, displayButton, sortButton, searchButton, modifyButton, attendanceButton, displayAttendanceButton;
    private Statement stmt;

    public AppGUI() {
        // Set up the frame
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());  // Using GridBagLayout for better control of layout
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between components

        // Initialize labels and set font/color
        studentIdLabel = createStyledLabel("Student ID:");
        firstNameLabel = createStyledLabel("First Name:");
        lastNameLabel = createStyledLabel("Last Name:");
        majorLabel = createStyledLabel("Major:");
        phoneLabel = createStyledLabel("Phone:");
        gpaLabel = createStyledLabel("GPA:");
        dobLabel = createStyledLabel("Date of Birth (yyyy-mm-dd):");

        // Initialize text fields
        studentIdField = new JTextField(15);
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        majorField = new JTextField(15);
        phoneField = new JTextField(15);
        gpaField = new JTextField(15);
        dobField = new JTextField(15);

        // Initialize buttons
        addButton = createStyledButton("Add");
        displayButton = createStyledButton("Display Students");
        sortButton = createStyledButton("Sort by GPA");
        searchButton = createStyledButton("Search by ID");
        modifyButton = createStyledButton("Modify Record");
        attendanceButton = createStyledButton("Mark Attendance");
        displayAttendanceButton = createStyledButton("Display Attendance");

        // Add action listeners
        addButton.addActionListener(this);
        displayButton.addActionListener(this);
        sortButton.addActionListener(this);
        searchButton.addActionListener(this);
        modifyButton.addActionListener(this);
        attendanceButton.addActionListener(this);
        displayAttendanceButton.addActionListener(this);

        // Add components to the layout (labels, text fields, buttons)
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(studentIdLabel, gbc);
        gbc.gridx = 1;
        frame.add(studentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        frame.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        frame.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(majorLabel, gbc);
        gbc.gridx = 1;
        frame.add(majorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(phoneLabel, gbc);
        gbc.gridx = 1;
        frame.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(gpaLabel, gbc);
        gbc.gridx = 1;
        frame.add(gpaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(dobLabel, gbc);
        gbc.gridx = 1;
        frame.add(dobField, gbc);

        // Add buttons
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(addButton, gbc);

        gbc.gridy = 8;
        frame.add(displayButton, gbc);

        gbc.gridy = 9;
        frame.add(sortButton, gbc);

        gbc.gridy = 10;
        frame.add(searchButton, gbc);

        gbc.gridy = 11;
        frame.add(modifyButton, gbc);

        gbc.gridy = 12;
        frame.add(attendanceButton, gbc);

        gbc.gridy = 13;
        frame.add(displayAttendanceButton, gbc);

        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }


    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(25, 100, 200));
        return label;
    }

    // Helper method to style buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 180, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);  // Remove focus border
        button.setPreferredSize(new Dimension(200, 30));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dbConnect db = new dbConnect();
        Connection conn;
        try {
            conn = db.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Table tb = new Table();

        if (e.getSource() == addButton) {
            // Insert new student into the database
            String sql = "INSERT INTO student VALUES('" + studentIdField.getText() + "', '"
                    + firstNameField.getText() + "', '" + lastNameField.getText() + "', '" + majorField.getText()
                    + "', '" + phoneField.getText() + "', '" + gpaField.getText() + "', '" + dobField.getText() + "')";
            try {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Student added successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == displayButton) {
            // Display all student data
            String sql = "SELECT * FROM student";
            try {
                ResultSet rs = stmt.executeQuery(sql);
                JTable table = new JTable(tb.buildTableModel(rs));
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == attendanceButton) {
            // Mark Attendance
            String studentId = JOptionPane.showInputDialog("Enter Student ID:");
            String date = JOptionPane.showInputDialog("Enter date (yyyy-mm-dd):");
            String status = JOptionPane.showInputDialog("Enter Attendance Status (Present/Absent):");

            String sql = "INSERT INTO attendance (student_id, date, status) VALUES('" + studentId + "', '" + date + "', '" + status + "')";
            try {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Attendance marked successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == displayAttendanceButton) {
            // Display all attendance data
            String sql = "SELECT * FROM attendance";
            try {
                ResultSet rs = stmt.executeQuery(sql);
                JTable table = new JTable(tb.buildTableModel(rs));
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == searchButton) {
            // Search student by ID
            String searchId = JOptionPane.showInputDialog("Enter Student ID to Search:");
            String sql = "SELECT * FROM student WHERE student_id = '" + searchId + "'";
            try {
                ResultSet rs = stmt.executeQuery(sql);
                JTable table = new JTable(tb.buildTableModel(rs));
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == sortButton) {
            // Sort students by GPA
            String sql = "SELECT * FROM student ORDER BY gpa DESC";
            try {
                ResultSet rs = stmt.executeQuery(sql);
                JTable table = new JTable(tb.buildTableModel(rs));
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == modifyButton) {
            // Modify student record (ask for all fields)
            String studentId = JOptionPane.showInputDialog("Enter Student ID to Modify:");
            String newFirstName = JOptionPane.showInputDialog("Enter New First Name:");
            String newLastName = JOptionPane.showInputDialog("Enter New Last Name:");
            String newMajor = JOptionPane.showInputDialog("Enter New Major:");
            String newPhone = JOptionPane.showInputDialog("Enter New Phone:");
            String newGPA = JOptionPane.showInputDialog("Enter New GPA:");
            String newDOB = JOptionPane.showInputDialog("Enter New Date of Birth (yyyy-mm-dd):");

            String sql = "UPDATE student SET first_name = '" + newFirstName + "', last_name = '" + newLastName
                    + "', major = '" + newMajor + "', phone = '" + newPhone + "', gpa = '" + newGPA + "', dob = '"
                    + newDOB + "' WHERE student_id = '" + studentId + "'";
            try {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Student record updated.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AppGUI();
    }
}
