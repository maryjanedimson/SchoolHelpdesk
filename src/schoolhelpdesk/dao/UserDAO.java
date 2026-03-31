package schoolhelpdesk.dao;

import schoolhelpdesk.models.User;
import schoolhelpdesk.database.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

/**
 * User Data Access Object
 * Handles authentication and user operations
 */
public class UserDAO {
    private static final String COLLECTION_NAME = "users";

    // Get MongoDB Collection
    private MongoCollection<User> getCollection() {
        return MongoDBConnection.getCollection(COLLECTION_NAME, User.class);
    }

    // Create a new user
    public boolean createUser(User user) {
        try {
            getCollection().insertOne(user);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    // Authenticate user
    public User authenticateUser(String username, String password) {
        try {
            return getCollection().find(
                    new com.mongodb.client.model.Filters().and(
                            eq("username", username),
                            eq("password", password)
                    )
            ).first();
        } catch (Exception e) {
            System.err.println("Error authenticating user: " + e.getMessage());
            return null;
        }
    }

    // Get user by username
    public User getUserByUsername(String username) {
        try {
            return getCollection().find(eq("username", username)).first();
        } catch (Exception e) {
            System.err.println("Error retrieving user: " + e.getMessage());
            return null;
        }
    }

    // Get user by ID
    public User getUserByID(String userID) {
        try {
            return getCollection().find(eq("userID", userID)).first();
        } catch (Exception e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            return null;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        try {
            return getCollection().find().into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error retrieving all users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get all admins
    public List<User> getAllAdmins() {
        try {
            return getCollection().find(eq("role", "ADMIN")).into(new ArrayList<>());
        } catch (Exception e) {
            System.err.println("Error retrieving admins: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Update user
    public boolean updateUser(User user) {
        try {
            getCollection().replaceOne(eq("username", user.getUsername()), user);
            return true;
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    // Delete user
    public boolean deleteUser(String username) {
        try {
            long deletedCount = getCollection().deleteOne(eq("username", username)).getDeletedCount();
            return deletedCount > 0;
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // Check if user exists
    public boolean userExists(String username) {
        try {
            return getCollection().find(eq("username", username)).first() != null;
        } catch (Exception e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    // Initialize default users (if none exist)
    public void initializeDefaultUsers() {
        try {
            if (getCollection().countDocuments() == 0) {
                User admin = new User("admin", "password123", "admin@school.edu", "USER");
                User system = new User("system", "system123", "system@school.edu", "ADMIN");
                createUser(admin);
                createUser(system);
                System.out.println("✓ Default users created");
            }
        } catch (Exception e) {
            System.err.println("Error initializing default users: " + e.getMessage());
        }
    }
}
