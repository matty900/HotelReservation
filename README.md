# **Project Overview** <br/>
The Hotel Reservation System is a multithreaded client-server application built using Java and JavaFX. The system allows users to make reservations and view reservation details dynamically, handling multiple clients simultaneously through a multithreaded server setup. This project demonstrates my understanding of multithreading, networking, and UI/UX design.

# **Features**<br/>
Multithreaded Server: Handles multiple clients concurrently using ServerSocket and Socket classes, with each client running on a separate thread.
Dynamic UI Updates: UI components update in real-time based on changes in the data model using JavaFX property bindings.
Synchronization Across Views: Ensures consistent data display across multiple views via property bindings.
Robust Input Validation: Validates user inputs and handles exceptions, ensuring a smooth user experience.
Client-Server Communication: Uses ObjectInputStream and ObjectOutputStream for serialized object communication.
Intuitive JavaFX UI: Responsive and visually appealing user interface.


# **Requirements**<br/>
To build and run the project, ensure you have the following installed:

Java JDK 11+: This project uses Java 11 or later versions.<br/>
Maven: Maven is required for managing dependencies and building the project.<br/>
JavaFX SDK: JavaFX libraries are necessary for the UI components.<br/>

# **Setup Instructions**<br/>

## Clone the Repository <br/>
git clone https://github.com/matty900/hotel-reservation-system.git
cd hotel-reservation-system<br/>
## Build the Project<br/>
Maven is used for dependency management and building the project. Use the following command to build the project:<br/>
./mvnw clean install
## Run the Server<br/>
To start the multithreaded server: <br/>
java -cp target/hotel-reservation-system.jar com.example.server.Server
## Run the Client <br/>
To run the client:<br/>
java -cp target/hotel-reservation-system.jar com.example.client.Client



# **Local Development**<br/>
## Prerequisites <br/>
IDE: IntelliJ IDEA or any Java IDE that supports Maven projects.<br/>
JavaFX: Ensure JavaFX is properly integrated with your IDE. You can follow this guide to set up JavaFX in your environment.
## Running Locally<br/>
1. Import the project into your IDE.<br/>
2. Run the Maven build to install dependencies and compile the project.<br/>
3. Start the server as outlined above, and run multiple clients to connect to the server and test the application.
