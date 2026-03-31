package schoolhelpdesk.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * MongoDB Connection Manager
 * Handles connection to MongoDB server
 */
public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    // Initialize connection
    public static void initialize() {
        if (mongoClient == null) {
            try {
                // Connect to MongoDB on localhost:27017
                mongoClient = MongoClients.create("mongodb://localhost:27017");
                database = mongoClient.getDatabase("SchoolHelpdesk");
                System.out.println("✓ MongoDB Connected Successfully!");
            } catch (Exception e) {
                System.err.println("✗ MongoDB Connection Failed:");
                System.err.println(e.getMessage());
                System.err.println("\nMake sure MongoDB is running on localhost:27017");
                System.err.println("Start MongoDB with: mongod");
            }
        }
    }

    // Get Database instance
    public static MongoDatabase getDatabase() {
        if (database == null) {
            initialize();
        }
        return database;
    }

    // Get Collection with POJO codec
    public static <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClients.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        return getDatabase().getCollection(collectionName, clazz).withCodecRegistry(pojoCodecRegistry);
    }

    // Close connection
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB Connection Closed");
        }
    }
}
