package schoolhelpdesk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * AdminDashboard - Admin interface to manage all tickets
 */
public class AdminDashboard extends JFrame {
    private JTable tblAllTickets;
    private JButton btnResolve;
    private JButton btnDelete;
    private JButton btnLogout;
    private DefaultTableModel tableModel;
    private JLabel lblAdminWelcome;
    private JLabel lblAllTicketsTitle;
    private JLabel lblStats;

    public AdminDashboard() {
        initComponents();
        loadMockData();
        updateStats();
    }

    private void initComponents() {
        setTitle("Admin Dashboard - All Tickets");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 244, 248));
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 118, 210));
        headerPanel.setPreferredSize(new Dimension(1000, 70));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblAdminWelcome = new JLabel("Admin Dashboard");
        lblAdminWelcome.setFont(new Font("Arial", Font.BOLD, 22));
        lblAdminWelcome.setForeground(Color.WHITE);
        headerPanel.add(lblAdminWelcome, BorderLayout.WEST);

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

        // Stats Panel
        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(new Color(240, 244, 248));
        statsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblStats = new JLabel("Total Tickets: 4 | Open: 2 | In Progress: 1 | Resolved: 1");
        lblStats.setFont(new Font("Arial", Font.PLAIN, 13));
        lblStats.setForeground(new Color(66, 133, 244));
        statsPanel.add(lblStats);

        mainPanel.add(statsPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(240, 244, 248));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Tickets Title and Buttons Panel
        JPanel ticketsHeaderPanel = new JPanel();
        ticketsHeaderPanel.setBackground(new Color(240, 244, 248));
        ticketsHeaderPanel.setLayout(new BorderLayout(10, 10));

        lblAllTicketsTitle = new JLabel("All Support Tickets");
        lblAllTicketsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        ticketsHeaderPanel.add(lblAllTicketsTitle, BorderLayout.WEST);

        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setBackground(new Color(240, 244, 248));
        actionButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        btnResolve = new JButton("Mark as Resolved");
        btnResolve.setFont(new Font("Arial", Font.BOLD, 12));
        btnResolve.setBackground(new Color(56, 142, 60));
        btnResolve.setForeground(Color.WHITE);
        btnResolve.setPreferredSize(new Dimension(140, 35));
        btnResolve.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnResolve.addActionListener(evt -> btnResolveActionPerformed(evt));
        actionButtonsPanel.add(btnResolve);

        btnDelete = new JButton("Delete Ticket");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        btnDelete.setBackground(new Color(211, 47, 47));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setPreferredSize(new Dimension(140, 35));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.addActionListener(evt -> btnDeleteActionPerformed(evt));
        actionButtonsPanel.add(btnDelete);

        ticketsHeaderPanel.add(actionButtonsPanel, BorderLayout.EAST);
        contentPanel.add(ticketsHeaderPanel, BorderLayout.NORTH);

        // Table with Scroll Pane
        String[] columnNames = {"Ticket ID", "User ID", "Department", "Issue", "Status", "Priority", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblAllTickets = new JTable(tableModel);
        tblAllTickets.setFont(new Font("Arial", Font.PLAIN, 11));
        tblAllTickets.setRowHeight(24);
        tblAllTickets.getTableHeader().setBackground(new Color(33, 150, 243));
        tblAllTickets.getTableHeader().setForeground(Color.WHITE);
        tblAllTickets.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblAllTickets.setSelectionBackground(new Color(174, 213, 250));

        JScrollPane scrollPane = new JScrollPane(tblAllTickets);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void loadMockData() {
        // Add sample data for admin view
        tableModel.addRow(new Object[]{"TKT-001", "USER001", "IT", "Cannot login to email", "Open", "High", "2026-03-29"});
        tableModel.addRow(new Object[]{"TKT-002", "USER002", "Maintenance", "Broken chair in room 101", "In Progress", "Medium", "2026-03-28"});
        tableModel.addRow(new Object[]{"TKT-003", "USER003", "Registrar", "Grade update request", "Resolved", "Low", "2026-03-25"});
        tableModel.addRow(new Object[]{"TKT-004", "USER001", "IT", "Printer not working", "Open", "Medium", "2026-03-30"});
    }

    private void updateStats() {
        int total = tableModel.getRowCount();
        int open = 0;
        int inProgress = 0;
        int resolved = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = (String) tableModel.getValueAt(i, 4);
            if (status.equals("Open")) open++;
            else if (status.equals("In Progress")) inProgress++;
            else if (status.equals("Resolved")) resolved++;
        }

        lblStats.setText("Total Tickets: " + total + " | Open: " + open + " | In Progress: " + inProgress + " | Resolved: " + resolved);
    }

    private void btnResolveActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblAllTickets.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket to resolve!", 
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String ticketID = (String) tableModel.getValueAt(selectedRow, 0);
        tableModel.setValueAt("Resolved", selectedRow, 4);
        JOptionPane.showMessageDialog(this, "Ticket " + ticketID + " marked as Resolved!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        updateStats();
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblAllTickets.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket to delete!", 
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this ticket?",
                "Delete Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Ticket deleted successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            updateStats();
        }
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
