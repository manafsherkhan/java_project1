/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import javax.swing.table.TableCellRenderer;
import java.util.HashMap;

import java.awt.*;
import java.sql.*;


public class JobSeekerPage extends JFrame {
    private String username;
    private boolean loggedIn = false;

    public JobSeekerPage() {
       
         
        setTitle("Job Seeker Page");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(panel);
        JButton button = new JButton("Login");
        JButton button1 = new JButton("Sign Up");
        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        panel.add(button);
        panel.add(button1);
        add(panel, BorderLayout.NORTH);
        button1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        setVisible(false); // Hide the current frame
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setVisible(true); // Show the SignUpPage frame
    }
});
        button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Make the JobSeekerPage frame invisible
        setVisible(false);

        // Create a new Loginpage frame and make it visible
        Loginpage loginpage = new Loginpage();
        loginpage.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
    // Make the JobSeekerPage frame visible again when the Loginpage frame is closed
    setVisible(true);

    Object loginResultObj = loginpage.isLoggedIn();
    HashMap<String, Object> loginResult = (HashMap<String, Object>) loginResultObj;

    loggedIn = (boolean) loginResult.get("loggedIn");
    username = (String) loginResult.get("username");
    
    JobSeekerPage.this.username = (String) loginResult.get("username");
    if (loggedIn) 
    {
        
        panel.remove(button);
        panel.remove(button1);
        welcomeLabel.setText("Welcome, " + username);
        panel.add(welcomeLabel);
        panel.revalidate();
        panel.repaint();
     }
    }
        });
        loginpage.setVisible(true);
    }
});
        String[] columnNames = {"Id" ,"Job Title", "Company", "Description", "Location", "Date Posted", "Apply"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Define a custom cell renderer for the "Apply" column
        class ButtonRenderer extends JButton implements TableCellRenderer {
            public ButtonRenderer() {
                setOpaque(true);
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText("Apply");
                return this;
            }
        }

        
        table.getColumn("Apply").setCellRenderer(new ButtonRenderer());
       class ApplyButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (JobSeekerPage.this.loggedIn == true) {
            // Retrieve the id from the selected row
            int selectedRow = table.getSelectedRow();
            int id = (int) table.getValueAt(selectedRow, 0);
            
            // Save the id to the MySQL database
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","leenu");
                Statement stmt=con.createStatement();
                String sql="INSERT INTO applied_job (job_id, username) VALUES (" + id + ", '" + username + "')";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Applied successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "You must be logged in to apply for this job.");
        }
    }
}

        class ButtonEditor extends DefaultCellEditor {
            protected JButton button;

            public ButtonEditor(JCheckBox checkBox) {
                super(checkBox);
                button = new JButton();
                button.setOpaque(true);
                button.addActionListener(new ApplyButtonListener());
            }

            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (isSelected) {
                    button.setForeground(table.getSelectionForeground());
                    button.setBackground(table.getSelectionBackground());
                } else {
                    button.setForeground(table.getForeground());
                    button.setBackground(table.getBackground());
                }
                button.setText("Apply");
                return button;
            }

            public Object getCellEditorValue() {
                return button.getText();
            }
        }

        table.getColumn("Apply").setCellEditor(new ButtonEditor(new JCheckBox()));

        setSize(500, 500);
        setVisible(true);

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project", "root", "leenu");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM jobpost");

            while (resultSet.next()) {
                JButton applyButton =new JButton("Apply");
                Object[] row = {
                        resultSet.getInt("id"),
                        resultSet.getString("job_title"),
                        resultSet.getString("company"),
                        resultSet.getString("descr"),
                        resultSet.getString("loc"),
                        resultSet.getDate("date_posted"),
                        applyButton
                };
                tableModel.addRow(row);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally 
          {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
          }        
    }
}