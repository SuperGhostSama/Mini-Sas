import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.JDBC;
public class Author {
    private int id;
    private String name;

    public Author(int id ) {
        this.id = id;
    }

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //METHODS

    public static String addAuthor(String name) {
        if (name.isEmpty()) {
            return "Author name cannot be empty. Please enter a valid name.";
        }

        // Check if the author name already exists
        if (isAuthorNameExists(name)) {
            return "Author with the same name already exists.";
        }

        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO author (name) VALUES (?)")) {

            preparedStatement.setString(1, name);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Author added successfully";
            } else {
                return "Failed to add author";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }



    public static String editAuthor(int id, String name) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE author SET name = ? WHERE id = ?")) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Author edited successfully";
            } else {
                return "Author not found or failed to edit";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


    public static String deleteAuthor(int id) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM author WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Author deleted successfully";
            } else {
                return "Author not found or failed to delete";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static List<Author> showAuthors() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement()) {

            // SQL query to select all authors
            String sqlQuery = "SELECT * FROM author";

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the result set and create Author objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                // Create an Author object and add it to the list
                Author author = new Author(id, name);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    private static boolean isAuthorNameExists(String name) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM author WHERE name = ?")) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Default to false if there was an error
        return false;
    }

}
