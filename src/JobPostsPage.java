



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class JobPostsPage extends JFrame implements ActionListener {

    private JLabel titleLabel, companyLabel, descrLabel, locLabel, dateLabel;
    private JTextField titleTextField, companyTextField, locTextField, dateTextField;
    private JTextArea descrTextArea;
    private JButton postButton;

    public JobPostsPage() {
        
        setTitle("Post a Job");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        
        titleLabel = new JLabel("Job Title:");
        companyLabel = new JLabel("Company:");
        descrLabel = new JLabel("Description:");
        locLabel = new JLabel("Location:");
        dateLabel = new JLabel("Date Posted (yyyy-mm-dd):");

        
        titleTextField = new JTextField();
        companyTextField = new JTextField();
        locTextField = new JTextField();
        dateTextField = new JTextField();

        descrTextArea = new JTextArea();

        postButton = new JButton("Post Job");
        postButton.addActionListener(this);

        add(titleLabel);
        add(titleTextField);
        add(companyLabel);
        add(companyTextField);
        add(descrLabel);
        add(descrTextArea);
        add(locLabel);
        add(locTextField);
        add(dateLabel);
        add(dateTextField);
        add(new JLabel()); 
        add(postButton);

        // Show the frame
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == postButton) {
            // Get the job data from the text fields
            String title = titleTextField.getText();
            String company = companyTextField.getText();
            String descr = descrTextArea.getText();
            String loc = locTextField.getText();
            String datePosted = dateTextField.getText();

            // Connect to the database and insert the job data
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "leenu");
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO jobpost (job_title, company, descr, loc, date_posted) VALUES ('" + title + "', '" + company + "', '" + descr + "', '" + loc + "', '" + datePosted + "')";
                stmt.executeUpdate(sql);
                conn.close();
                JOptionPane.showMessageDialog(this, "Job posted successfully.");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error posting job: " + ex.getMessage());
            }
        }
    }
}
