package schoolhelpdesk;

import javax.swing.*;
import java.awt.*;

/**
 * SubmitIssueForm - Form for users to submit new support tickets
 */
public class SubmitIssueForm extends JFrame {
    private JLabel lblTitle;
    private JLabel lblDepartment;
    private JLabel lblIssue;
    private JComboBox<String> cmbDepartment;
    private JTextArea txtIssue;
    private JButton btnSubmit;
    private JButton btnCancel;
    private JLabel lblPriority;
    private JComboBox<String> cmbPriority;

    public SubmitIssueForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Submit New Support Ticket");
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 244, 248));
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 118, 210));
        headerPanel.setPreferredSize(new Dimension(600, 60));
        lblTitle = new JLabel("Submit a New Support Ticket");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(240, 244, 248));
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Department Label and Combo
        lblDepartment = new JLabel("Select Department:");
        lblDepartment.setFont(new Font("Arial", Font.BOLD, 13));
        lblDepartment.setBounds(30, 30, 200, 25);
        formPanel.add(lblDepartment);

        cmbDepartment = new JComboBox<>(new String[]{"-- Select Department --", "IT", "Maintenance", "Registrar"});
        cmbDepartment.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbDepartment.setBounds(30, 60, 540, 35);
        formPanel.add(cmbDepartment);

        // Priority Label and Combo
        lblPriority = new JLabel("Priority Level:");
        lblPriority.setFont(new Font("Arial", Font.BOLD, 13));
        lblPriority.setBounds(30, 110, 200, 25);
        formPanel.add(lblPriority);

        cmbPriority = new JComboBox<>(new String[]{"Low", "Medium", "High", "Critical"});
        cmbPriority.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbPriority.setBounds(30, 140, 540, 35);
        formPanel.add(cmbPriority);

        // Issue Label and Text Area
        lblIssue = new JLabel("Describe Your Issue:");
        lblIssue.setFont(new Font("Arial", Font.BOLD, 13));
        lblIssue.setBounds(30, 190, 200, 25);
        formPanel.add(lblIssue);

        txtIssue = new JTextArea();
        txtIssue.setFont(new Font("Arial", Font.PLAIN, 12));
        txtIssue.setLineWrap(true);
        txtIssue.setWrapStyleWord(true);
        txtIssue.setBounds(30, 220, 540, 150);
        txtIssue.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        formPanel.add(txtIssue);

        JScrollPane scrollPane = new JScrollPane(txtIssue);
        scrollPane.setBounds(30, 220, 540, 150);
        formPanel.add(scrollPane);

        // Submit Button
        btnSubmit = new JButton("Submit Ticket");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 13));
        btnSubmit.setBackground(new Color(56, 142, 60));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(100, 400, 140, 40);
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(evt -> btnSubmitActionPerformed(evt));
        formPanel.add(btnSubmit);

        // Cancel Button
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancel.setBackground(new Color(158, 158, 158));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBounds(360, 400, 140, 40);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.addActionListener(evt -> btnCancelActionPerformed(evt));
        formPanel.add(btnCancel);

        JScrollPane formScroll = new JScrollPane(formPanel);
        formScroll.setBorder(null);
        mainPanel.add(formScroll, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {
        String department = (String) cmbDepartment.getSelectedItem();
        String priority = (String) cmbPriority.getSelectedItem();
        String issue = txtIssue.getText();

        if (department.equals("-- Select Department --") || issue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a department and describe your issue!", 
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Frontend-only: Show confirmation message with ticket number
        String ticketNumber = "TKT-" + String.format("%03d", (int)(Math.random() * 999));
        JOptionPane.showMessageDialog(this, 
                "Ticket " + ticketNumber + " has been submitted successfully!\n" +
                "Department: " + department + "\n" +
                "Priority: " + priority + "\n" +
                "Status: Pending",
                "Ticket Submitted", 
                JOptionPane.INFORMATION_MESSAGE);

        this.dispose();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
}
