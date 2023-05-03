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

class SignUpPage extends JFrame implements ActionListener {

    JLabel nameLabel, emailLabel, passwordLabel;
    JTextField nameTextField, emailTextField, passwordTextField;
    JButton signUpButton;

    SignUpPage() {
        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 50, 100, 30);
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 100, 100, 30);
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 150, 100, 30);

        nameTextField = new JTextField();
        nameTextField.setBounds(150, 50, 150, 30);
        emailTextField = new JTextField();
        emailTextField.setBounds(150, 100, 150, 30);
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150, 150, 150, 30);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(150, 200, 100, 30);
        signUpButton.addActionListener(this);

        add(nameLabel);
        add(emailLabel);
        add(passwordLabel);
        add(nameTextField);
        add(emailTextField);
        add(passwordTextField);
        add(signUpButton);

        setTitle("Sign Up");
        setSize(400, 300);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == signUpButton) {
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "leenu");
            Statement stmt = con.createStatement();
            String query = "INSERT INTO job_seeeker (first_name, last_name, email, pass) VALUES ('" + name + "', '', '" + email + "', '" + password + "')";
            int result = stmt.executeUpdate(query);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Sign Up Successful!");
                
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sign Up Failed!");
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}


    
}

