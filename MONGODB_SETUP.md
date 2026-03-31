================================================================================
    MONGODB INTEGRATION - SETUP GUIDE
================================================================================

✅ STEP 1: DOWNLOAD MONGODB DRIVER FOR JAVA

The project uses MongoDB Java Driver. You need to add it to your NetBeans project.

Option A: DOWNLOAD JAR FILES (Recommended for beginners)
   1. Download MongoDB Java Driver from:
      https://www.mongodb.com/try/download/java-driver
      
   2. Extract the ZIP file
   
   3. You'll need these JAR files:
      - mongodb-driver-sync-4.x.x.jar
      - mongodb-driver-core-4.x.x.jar
      - bson-4.x.x.jar
      - bson-record-codec-4.x.x.jar

Option B: USE MAVEN (If you want automated dependency management)
   - See MAVEN_SETUP.md (to be created)

================================================================================
✅ STEP 2: ADD JARS TO NETBEANS PROJECT

1. Right-click Project → Properties
2. Libraries Tab → Add JAR/Folder
3. Select all downloaded JAR files (from Step 1)
4. Click Open → Apply → OK

================================================================================
✅ STEP 3: INSTALL & RUN MONGODB

Windows:
   1. Download: https://www.mongodb.com/try/download/community
   2. Run installer (mongodb-windows-x86_64-X.X.X.msi)
   3. Follow installation wizard
   4. Open Command Prompt and run: mongod
   5. You should see: "Waiting for connections on port 27017"

Mac:
   1. Install via Homebrew: brew tap mongodb/brew
   2. Install: brew install mongodb-community
   3. Start: brew services start mongodb-community
   4. Or run: mongod

Linux:
   1. Follow MongoDB official installation guide
   2. Start: sudo systemctl start mongod

================================================================================
✅ STEP 4: VERIFY CONNECTION

Before running the app, verify MongoDB is running:
   1. Open MongoDB Compass (GUI client) - installed with MongoDB
   2. Connect to: mongodb://localhost:27017
   3. You should see "admin", "config", "local" databases

OR use MongoDB Shell:
   1. Open new Command Prompt
   2. Type: mongosh
   3. You should see: >

================================================================================
✅ STEP 5: RUN YOUR APPLICATION IN NETBEANS

1. In NetBeans, click Run (Shift + F6)
2. You should see:
   ✓ MongoDB Connected Successfully!
   ✓ Default users created

3. If you see errors, check:
   - Is MongoDB running? (mongod command)
   - Are all JARs added to the project?
   - Check the Output panel for error messages

================================================================================
📊 DATABASE STRUCTURE

The application creates these collections:

USERS Collection:
{
  "_id": ObjectId,
  "userID": "USER001",
  "username": "admin",
  "password": "password123",
  "email": "admin@school.edu",
  "role": "USER",
  "department": "IT",
  "createdDate": ISODate,
  "active": true
}

TICKETS Collection:
{
  "_id": ObjectId,
  "ticketID": "TKT-0001",
  "userID": "USER001",
  "department": "IT",
  "issue": "Cannot login to email",
  "status": "Open",
  "priority": "High",
  "dateSubmitted": ISODate,
  "dateResolved": null,
  "resolution": null
}

================================================================================
🔑 DEFAULT CREDENTIALS

First time you run:
   User Account:    username = "admin"    password = "password123"
   Admin Account:   username = "system"   password = "system123"

You can create more users by updating UserDAO.initializeDefaultUsers()

================================================================================
⚠️ TROUBLESHOOTING

Error: "MongoDB Connection Failed"
   → Make sure MongoDB is running (mongod command)
   → Check if it's on port 27017
   
Error: "Exception in thread main: java.lang.ClassNotFoundException"
   → JAR files not added to project
   → Re-check Step 2: Add JAR/Folder to Libraries
   
Error: "No suitable driver found"
   → Same as above - add JARs to classpath
   
Data not saving?
   → Check MongoDB is running
   → Check database name is "SchoolHelpdesk"
   → Use MongoDB Compass to verify

================================================================================
📝 USEFUL COMMANDS

View all databases:
   > show dbs

Use SchoolHelpdesk database:
   > use SchoolHelpdesk

View all collections:
   > show collections

View all tickets:
   > db.tickets.find()

Clear all data:
   > db.tickets.deleteMany({})
   > db.users.deleteMany({})

================================================================================
✨ Next Steps After Setup

1. Run application - LoginForm opens
2. Login with admin/password123 (User) or system/system123 (Admin)
3. Submit tickets → Stored in MongoDB
4. View your tickets → Retrieved from database
5. Admin dashboard shows all tickets from database
6. Data persists even after closing app!

================================================================================
