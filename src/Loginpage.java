/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;


public class Loginpage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public boolean loggedIn = false;

    public Loginpage() {
        setTitle("Login");
        JPanel panel = new JPanel(new GridLayout(3, 2));
        // Add the username label and text field to the panel
        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);
        // Add the password label and password field to the panel
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);
        // Add the login button to the panel
        loginButton = new JButton("Login");
        panel.add(new JLabel(""));
        panel.add(loginButton);
        // Add the panel to the window
        add(panel, BorderLayout.CENTER);
        // Add the action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the username and password from the form fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Connect to the database and validate the user credentials
                Connection connection = null;
                try {
                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Create a connection to the database
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/project", "root", "leenu");
                    // Create a prepared statement to select the user with the given username and password
                    String query = "SELECT * FROM job_seeeker WHERE email = ? AND pass = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    // Execute the query
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        loggedIn = true;
                        resultSet.close();
                        statement.close();
                        connection.close();
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                        passwordField.setText("");
                    }
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                } finally {
                                        if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        setSize(300, 150);
        setVisible(true);
    }
    public HashMap<String, Object> isLoggedIn() {
    HashMap<String, Object> result = new HashMap<>();
    if (loggedIn) {
        result.put("loggedIn", true);
        result.put("username", usernameField.getText());
    } else {
        result.put("loggedIn", false);
    }
    return result;
}

}
