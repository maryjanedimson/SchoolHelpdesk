================================================================================
    SCHOOL HELPDESK SYSTEM - JAVA SWING FRONTEND
    Frontend Only (No Database Required Yet)
================================================================================

📁 PROJECT STRUCTURE:
    src/schoolhelpdesk/
    ├── Main.java                    (Entry Point)
    ├── LoginForm.java               (Login Screen)
    ├── UserDashboard.java           (User's Ticket View)
    ├── SubmitIssueForm.java         (Submit New Ticket)
    └── AdminDashboard.java          (Admin's Main Console)

================================================================================
🚀 HOW TO RUN IN NETBEANS:

1. OPEN PROJECT:
   - Open NetBeans
   - File → Open Project → Select "SchoolHelpdeskSystem" folder
   
2. COMPILE & RUN:
   - Click "Run" (or Shift + F6)
   - Main.java will start LoginForm automatically

3. TEST LOGIN:
   User Account:  username = "admin"  password = any
   Admin Account: username = "system" password = any
   
   OR Click "Demo Mode" button to go directly to User Dashboard

================================================================================
🎯 FEATURES IMPLEMENTED:

✅ LoginForm
   - Username & Password fields
   - Demo Mode button for quick testing
   - Validates input
   - Routes to User or Admin Dashboard

✅ UserDashboard
   - Table showing user's submitted tickets
   - "Submit New Issue" button
   - Mock data (4 sample tickets)
   - Logout functionality

✅ SubmitIssueForm
   - Department selector (IT, Maintenance, Registrar)
   - Priority level selector (Low, Medium, High, Critical)
   - Issue description text area
   - Auto-generates ticket number on submission
   - Submit/Cancel buttons

✅ AdminDashboard
   - Table with ALL tickets (User ID, Department, Issue, Status, Priority, Date)
   - Statistics panel (Total, Open, In Progress, Resolved count)
   - "Mark as Resolved" button
   - "Delete Ticket" button
   - Real-time stats update
   - Logout functionality

================================================================================
🎨 DESIGN HIGHLIGHTS:

- Professional Blue Color Scheme (Material Design inspired)
- Responsive layouts with proper spacing
- Clear button labels and icons
- Tables with scroll panes for easy data viewing
- Input validation on all forms
- Confirmation dialogs for critical actions
- Font sizing for readability (12-24pt)

================================================================================
🔄 NAVIGATION FLOW:

    LoginForm
       ↓ (login/demo)
    ┌──────────────┐
    ↓              ↓
UserDashboard   AdminDashboard
    ↓
SubmitIssueForm (dialog)

All forms have Logout buttons to return to LoginForm

================================================================================
💾 NEXT STEPS (When Adding Database):

1. Create Database Connection class
2. Replace mock data with SQL queries
3. Connect buttons to actual database operations:
   - INSERT tickets from SubmitIssueForm
   - SELECT tickets for dashboards
   - UPDATE ticket status via AdminDashboard
   - DELETE tickets

4. Add User Authentication with password hashing
5. Add error handling and logging

================================================================================
📝 SAMPLE MOCK DATA (Currently Hardcoded):

User's Dashboard Shows:
- TKT-001 | IT | Cannot login to email | Open | 2026-03-29
- TKT-002 | Maintenance | Broken chair in room 101 | In Progress | 2026-03-28
- TKT-003 | Registrar | Grade update request | Resolved | 2026-03-25
- TKT-004 | IT | Printer not working | Open | 2026-03-30

Admin Dashboard Shows Same (with User ID column added)

================================================================================
🛠️ CUSTOMIZATION TIPS:

1. COLORS: Search "new Color(" in any file to change RGB colors
2. FONTS: Change Font("Arial", Font.BOLD, SIZE) to customize text
3. COMPONENTS: Add JLabel, JButton, JTextArea directly in initComponents()
4. TABLE COLUMNS: Modify columnNames array to add/remove columns
5. WINDOW SIZE: Change setSize(width, height) in initComponents()

================================================================================
✨ Version: 1.0 (Frontend Only)
   Last Updated: March 2026
================================================================================
