package schoolhelpdesk.models;

import org.bson.types.ObjectId;
import java.util.Date;

/**
 * Ticket Model - Represents a support ticket
 */
public class Ticket {
    private ObjectId _id;
    private String ticketID;
    private String userID;
    private String department;
    private String issue;
    private String status;          // Open, In Progress, Resolved
    private String priority;        // Low, Medium, High, Critical
    private Date dateSubmitted;
    private Date dateResolved;
    private String resolution;

    // Default Constructor
    public Ticket() {
    }

    // Constructor with main fields
    public Ticket(String userID, String department, String issue, String priority) {
        this.userID = userID;
        this.department = department;
        this.issue = issue;
        this.priority = priority;
        this.status = "Open";
        this.dateSubmitted = new Date();
    }

    // Getters & Setters
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
