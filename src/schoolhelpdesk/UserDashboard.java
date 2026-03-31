package schoolhelpdesk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * UserDashboard - User's main interface to view and submit tickets
 */
public class UserDashboard extends JFrame {
    private JTable tblTickets;
    private JButton btnSubmitIssue;
    private JButton btnLogout;
    private DefaultTableModel tableModel;
    private JLabel lblWelcome;
    private JLabel lblTicketsTitle;

    public UserDashboard() {
        initComponents();
        loadMockData();
    }

    private void initComponents() {
        setTitle("User Dashboard - Your Tickets");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 244, 248));
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 118, 210));
        headerPanel.setPreferredSize(new Dimension(900, 70));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblWelcome = new JLabel("Welcome, User!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setForeground(Color.WHITE);
        headerPanel.add(lblWelcome, BorderLayout.WEST);

        JPanel headerButtonPanel = new JPanel();
        headerButtonPanel.setBackground(new Color(25, 118, 210));
        headerButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogout.setBackground(new Color(211, 47, 47));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setPreferredSize(new Dimension(100, 40));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(evt -> btnLogoutActionPerformed(evt));
        headerButtonPanel.add(btnLogout);

        headerPanel.add(headerButtonPanel, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(240, 244, 248));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tickets Title and Button Panel
        JPanel ticketsHeaderPanel = new JPanel();
        ticketsHeaderPanel.setBackground(new Color(240, 244, 248));
        ticketsHeaderPanel.setLayout(new BorderLayout(10, 10));

        lblTicketsTitle = new JLabel("Your Support Tickets");
        lblTicketsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        ticketsHeaderPanel.add(lblTicketsTitle, BorderLayout.WEST);

        btnSubmitIssue = new JButton("+ Submit New Issue");
        btnSubmitIssue.setFont(new Font("Arial", Font.BOLD, 12));
        btnSubmitIssue.setBackground(new Color(56, 142, 60));
        btnSubmitIssue.setForeground(Color.WHITE);
        btnSubmitIssue.setPreferredSize(new Dimension(150, 35));
        btnSubmitIssue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmitIssue.addActionListener(evt -> btnSubmitIssueActionPerformed(evt));
        ticketsHeaderPanel.add(btnSubmitIssue, BorderLayout.EAST);

        contentPanel.add(ticketsHeaderPanel, BorderLayout.NORTH);

        // Table with Scroll Pane
        String[] columnNames = {"Ticket ID", "Department", "Issue", "Status", "Date Submitted"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblTickets = new JTable(tableModel);
        tblTickets.setFont(new Font("Arial", Font.PLAIN, 12));
        tblTickets.setRowHeight(25);
        tblTickets.getTableHeader().setBackground(new Color(33, 150, 243));
        tblTickets.getTableHeader().setForeground(Color.WHITE);
        tblTickets.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblTickets.setSelectionBackground(new Color(174, 213, 250));

        JScrollPane scrollPane = new JScrollPane(tblTickets);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void loadMockData() {
        // Add sample data to demonstrate the table
        tableModel.addRow(new Object[]{"TKT-001", "IT", "Cannot login to email", "Open", "2026-03-29"});
        tableModel.addRow(new Object[]{"TKT-002", "Maintenance", "Broken chair in room 101", "In Progress", "2026-03-28"});
        tableModel.addRow(new Object[]{"TKT-003", "Registrar", "Grade update request", "Resolved", "2026-03-25"});
        tableModel.addRow(new Object[]{"TKT-004", "IT", "Printer not working", "Open", "2026-03-30"});
    }

    private void btnSubmitIssueActionPerformed(java.awt.event.ActionEvent evt) {
        new SubmitIssueForm().setVisible(true);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to logout?", 
                "Logout Confirmation", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }
}
