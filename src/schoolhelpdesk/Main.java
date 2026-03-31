package schoolhelpdesk;

import schoolhelpdesk.database.MongoDBConnection;
import schoolhelpdesk.dao.UserDAO;

/**
 * School Helpdesk System - With MongoDB Integration
 * Main entry point for the application
 */
public class Main {
    public static void main(String[] args) {
        // Initialize MongoDB Connection
        MongoDBConnection.initialize();
        
        // Initialize default users if needed
        UserDAO userDAO = new UserDAO();
        userDAO.initializeDefaultUsers();
        
        // Start with LoginForm
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
