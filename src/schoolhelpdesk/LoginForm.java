package schoolhelpdesk;

import javax.swing.*;
import java.awt.*;

/**
 * LoginForm - User/Admin Authentication
 * Frontend Only (no database yet)
 */
public class LoginForm extends JFrame {
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnDemo;
    private JLabel lblTitle;
    private JLabel lblInfo;

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("School Helpdesk System - Login");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 244, 248));
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(25, 118, 210));
        titlePanel.setPreferredSize(new Dimension(500, 80));
        lblTitle = new JLabel("School Helpdesk System");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(lblTitle);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(240, 244, 248));
        formPanel.setLayout(null);

        // Username Label and Field
        lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsername.setBounds(80, 50, 100, 30);
        formPanel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBounds(80, 80, 340, 35);
        formPanel.add(txtUsername);

        // Password Label and Field
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setBounds(80, 125, 100, 30);
        formPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBounds(80, 155, 340, 35);
        formPanel.add(txtPassword);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(25, 118, 210));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(80, 210, 160, 40);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(evt -> btnLoginActionPerformed(evt));
        formPanel.add(btnLogin);

        // Demo Button
        btnDemo = new JButton("Demo Mode");
        btnDemo.setFont(new Font("Arial", Font.BOLD, 14));
        btnDemo.setBackground(new Color(56, 142, 60));
        btnDemo.setForeground(Color.WHITE);
        btnDemo.setBounds(260, 210, 160, 40);
        btnDemo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDemo.addActionListener(evt -> btnDemoActionPerformed(evt));
        formPanel.add(btnDemo);

        // Info Label
        lblInfo = new JLabel("Demo: User='admin' / Admin='system'");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setForeground(new Color(100, 100, 100));
        lblInfo.setBounds(80, 270, 340, 20);
        formPanel.add(lblInfo);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Frontend-only: Just check if fields are filled
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password!", 
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Simple demo logic
        if (username.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Welcome, User!", "Login Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            openUserDashboard();
        } else if (username.equals("system")) {
            JOptionPane.showMessageDialog(this, "Welcome, Admin!", "Login Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            openAdminDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Failed", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnDemoActionPerformed(java.awt.event.ActionEvent evt) {
        // Open UserDashboard in demo mode
        openUserDashboard();
    }

    private void openUserDashboard() {
        new UserDashboard().setVisible(true);
        this.dispose();
    }

    private void openAdminDashboard() {
        new AdminDashboard().setVisible(true);
        this.dispose();
    }
}
