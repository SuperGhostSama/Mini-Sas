import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.JDBC;

public class LibraryUser {
    private int id;
    private String name;
    private String email;
    private String phone;

    // Constructor
    public LibraryUser(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public LibraryUser(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    // Static list to store users (for demonstration purposes)
    private static List<LibraryUser> users = new ArrayList<>();

    // Method to add a user
    public static String addUser(String name, String email, String phone) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO libraryuser (name, email, phone) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "User added successfully";
            } else {
                return "Failed to add user";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


    // Method to delete a user by ID
    public static String deleteUser(int userId) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM libraryuser WHERE id = ?")) {

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "User deleted successfully";
            } else {
                return "User not found or failed to delete";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


    // Method to show all users
    public static List<LibraryUser> showUsers() {
        List<LibraryUser> users = new ArrayList<>();

        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM libraryuser";

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                LibraryUser user = new LibraryUser(id, name, email, phone);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}
