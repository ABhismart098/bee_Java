import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationApi {

    // JDBC URL with the correct database name
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bbe";

    // MySQL username
    private static final String USERNAME = "localhost"; // Replace with your MySQL username

    // MySQL password
    private static final String PASSWORD = "9899803387Ak@";

    // SQL query to insert user registration data
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return; // Exit the program if the driver is not found
        }

        // Sample registration data
        String username = "john_doe";
        String password = "secure_password";
        String email = "john.doe@example.com";

        // Register user
        registerUser(username, password, email);
    }

    private static void registerUser(String username, String password, String email) {
        try (
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            // Set parameters for the SQL query
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);

            // Execute the SQL query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Failed to register user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
