import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.JDBC;

public class Book {
    private int id;
    private String title;
    private Author author;//
    private String isbn;
    private int quantity;
    private int available;
    private int reserved;
    private int lost;

    public Book(int id, String title, Author author, String isbn, int quantity, int available, int reserved, int lost) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.available = available;
        this.reserved = reserved;
        this.lost = lost;
    }

    public Book(String title, Author author, String isbn, int quantity, int available, int reserved, int lost) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.available = available;
        this.reserved = reserved;
        this.lost = lost;
    }
    @Override
    public String toString() {
        return String.format("%-4s %-50s %-30s %-30s %-10s %-10s %-10s %-10s%n",
                "ID", "Title", "Author", "ISBN", "Quantity", "Available", "Reserved", "Lost") +
                String.format("%-4d %-50s %-30s %-30s %-10d %-10d %-10d %-10d%n",
                        id, truncateText(title, 50), truncateText(author.getName(), 30), isbn,
                        quantity, available, reserved, lost);
    }

    // Helper method to truncate text if it exceeds a specified length
    private static String truncateText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }




    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailable() {
        return available;
    }

    public int getReserved() {
        return reserved;
    }

    public int getLost() {
        return lost;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    //METHODS
    public static List<Book> showBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement()) {

            // SQL query to select all books from the 'books' table
            String sqlQuery = "SELECT books.*, author.name AS author_name FROM books " +
                    "INNER JOIN author ON books.author_id = author.id";

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the result set and create Book objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                String authorName = resultSet.getString("author_name");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                int available = resultSet.getInt("available");
                int reserved = resultSet.getInt("reserved");
                int lost = resultSet.getInt("lost");

                // Create an Author object
                Author author = new Author(authorId,authorName);

                // Create a Book object and add it to the list
                Book book = new Book(id, title, author,isbn, quantity, available, reserved, lost);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public String addBook(){

        String insertSql = "INSERT INTO books (title, author_id, isbn, quantity, available, reserved, lost) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, this.title);
            statement.setObject(2, this.author.getId());
            statement.setString(3, this.isbn);
            statement.setInt(4, this.quantity);
            statement.setInt(5, this.available);
            statement.setInt(6, this.reserved);
            statement.setInt(7, this.lost);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        return "Added successfully";
    }

    public String editBook() {
        String updateSql = "UPDATE books SET title = ?, author_id = ?,isbn = ?, quantity = ?, available = ?, reserved = ?, lost = ? WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSql)) {

            // Set the parameters in the prepared statement
            statement.setString(1, this.title);
            statement.setObject(2, this.author.getId());
            statement.setString(3, this.isbn);
            statement.setInt(4, this.quantity);
            statement.setInt(5, this.available);
            statement.setInt(6, this.reserved);
            statement.setInt(7, this.lost);
            statement.setInt(8, this.id);

            // Execute the update statement
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return "Edited successfully";
            } else {
                return "Book with ID " + id + " not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error editing book: " + e.getMessage();
        }
    }

    public static String deleteBook(int id) {
        String deleteSql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            // Set the id parameter in the prepared statement
            statement.setInt(1, id);

            // Execute the delete statement
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                return "Deleted successfully";
            } else {
                return "Book with ID " + id + " not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting book: " + e.getMessage();
        }
    }

    public static List<Book> searchBooksByTitle(String titleQuery) {
        List<Book> matchingBooks = new ArrayList<>();

        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT books.*, author.name AS author_name FROM books INNER JOIN author ON books.author_id = author.id WHERE title LIKE ?")) {

            // Set the search query for title using wildcard %
            String likeQuery = "%" + titleQuery + "%";
            preparedStatement.setString(1, likeQuery);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No books by the specified title found.");
                return matchingBooks;
            }

            // Process the result set and create Book objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                String authorName = resultSet.getString("author_name");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                int available = resultSet.getInt("available");
                int reserved = resultSet.getInt("reserved");
                int lost = resultSet.getInt("lost");

                // Create an Author object
                Author author = new Author(authorId, authorName);

                // Create a Book object and add it to the list
                Book book = new Book(id, title, author, isbn, quantity, available, reserved, lost);
                matchingBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingBooks;
    }

    public static List<Book> searchBooksByAuthor(String authorQuery) {
        List<Book> matchingBooks = new ArrayList<>();

        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT books.*, author.name AS author_name FROM books INNER JOIN author ON books.author_id = author.id WHERE author.name LIKE ?")) {

            // Set the search query for author name using wildcard %
            String likeQuery = "%" + authorQuery + "%";
            preparedStatement.setString(1, likeQuery);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No books by the specified author found.");
                return matchingBooks;
            }

            // Process the result set and create Book objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                String authorName = resultSet.getString("author_name");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                int available = resultSet.getInt("available");
                int reserved = resultSet.getInt("reserved");
                int lost = resultSet.getInt("lost");

                // Create an Author object
                Author author = new Author(authorId, authorName);

                // Create a Book object and add it to the list
                Book book = new Book(id, title, author, isbn, quantity, available, reserved, lost);
                matchingBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingBooks;
    }

}
