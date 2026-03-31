package schoolhelpdesk;

/**
 * School Helpdesk System - Frontend Only
 * Main entry point for the application
 */
public class Main {
    public static void main(String[] args) {
        // Start with LoginForm
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
