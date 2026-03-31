package schoolhelpdesk.dao;

import schoolhelpdesk.models.Ticket;
import schoolhelpdesk.database.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Ticket Data Access Object
 * Handles all database operations for tickets
 */
public class TicketDAO {
    private static final String COLLECTION_NAME = "tickets";

    // Get MongoDB Collection
    private MongoCollection<Ticket> getCollection() {
        return MongoDBConnection.getCollection(COLLECTION_NAME, Ticket.class);
    }

    // Generate unique Ticket ID
    public String generateTicketID() {
        return "TKT-" + String.format("%04d", (int)(Math.random() * 9999));
    }

    // Insert a new ticket
    public String insertTicket(Ticket ticket) {
        try {
            ticket.setTicketID(generateTicketID());
            getCollection().insertOne(ticket);
            return ticket.getTicketID();
        } catch (Exception e) {
            System.err.println("Error inserting ticket: " + e.getMessage());
            return null;
        }
    }

    // Get all tickets
    public List<Ticket> getAllTickets() {
        try {
            return getCollection().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error retrieving all tickets: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get tickets by User ID
    public List<Ticket> getTicketsByUserID(String userID) {
        try {
            return getCollection().find(eq("userID", userID)).into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error retrieving user tickets: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get ticket by Ticket ID
    public Ticket getTicketByID(String ticketID) {
        try {
            return getCollection().find(eq("ticketID", ticketID)).first();
        } catch (Exception e) {
            System.err.println("Error retrieving ticket: " + e.getMessage());
            return null;
        }
    }

    // Get tickets by Department
    public List<Ticket> getTicketsByDepartment(String department) {
        try {
            return getCollection().find(eq("department", department)).into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error retrieving department tickets: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get tickets by Status
    public List<Ticket> getTicketsByStatus(String status) {
        try {
            return getCollection().find(eq("status", status)).into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error retrieving status tickets: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Update ticket status
    public boolean updateTicketStatus(String ticketID, String newStatus) {
        try {
            Ticket ticket = getTicketByID(ticketID);
            if (ticket != null) {
                ticket.setStatus(newStatus);
                if (newStatus.equals("Resolved")) {
                    ticket.setDateResolved(new Date());
                }
                getCollection().replaceOne(eq("ticketID", ticketID), ticket);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error updating ticket status: " + e.getMessage());
            return false;
        }
    }

    // Delete ticket by ID
    public boolean deleteTicket(String ticketID) {
        try {
            long deletedCount = getCollection().deleteOne(eq("ticketID", ticketID)).getDeletedCount();
            return deletedCount > 0;
        } catch (Exception e) {
            System.err.println("Error deleting ticket: " + e.getMessage());
            return false;
        }
    }

    // Get ticket count by status
    public long getTicketCountByStatus(String status) {
        try {
            return getCollection().countDocuments(eq("status", status));
        } catch (Exception e) {
            System.err.println("Error counting tickets: " + e.getMessage());
            return 0;
        }
    }

    // Get total ticket count
    public long getTotalTicketCount() {
        try {
            return getCollection().countDocuments();
        } catch (Exception e) {
            System.err.println("Error counting total tickets: " + e.getMessage());
            return 0;
        }
    }
}
