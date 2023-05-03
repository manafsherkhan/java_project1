/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**


 *
 * @author Hp
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Index {
    public static void main(String[] args) {
        
        
    
       
        String url = "jdbc:mysql://localhost/root";
        String username = "root";
        String password = "leenu";
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    catch(ClassNotFoundException c)
    {
        c.printStackTrace();
    }

        JFrame frame = new JFrame("Career Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        JLabel titleLabel = new JLabel("Welcome to the Career Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JButton jobSeekersButton = new JButton("Job Seekers");
        JButton jobPostsButton = new JButton("Post Job");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10); // Add some padding
        panel.add(titleLabel, constraints);
        constraints.gridy = 1;
        panel.add(jobSeekersButton, constraints);
        constraints.gridy = 2;
        panel.add(jobPostsButton, constraints);
        frame.getContentPane().add(panel);

        // Add event listeners to the buttons
        jobSeekersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JobSeekerPage jobSeekerPage = new JobSeekerPage();
              jobSeekerPage.setVisible(true);
              frame.dispose();
            }
        });
        jobPostsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                JobPostsPage jobPostsPage = new JobPostsPage();
               jobPostsPage.setVisible(true);
               frame.dispose();
            }
        });
        frame.setVisible(true);
    }
}