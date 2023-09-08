import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.JDBC;

public class BorrowingRecord {
    private int id;
    private Book book;//
    private LibraryUser libraryUser;//
    private Date borrowDate;
    private Date returnDate;

    public BorrowingRecord(int id, Book book, LibraryUser libraryUser, Date borrowDate, Date returnDate) {
        this.id = id;
        this.book = book;
        this.libraryUser = libraryUser;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    //METHODS
    public static String borrowBook(String isbn, String libraryUserName, Date borrowDate, Date returnDate) {
        try (Connection connection = JDBC.getConnection()) {
            // Check if the book with the given ISBN exists
            String selectBookSql = "SELECT id, available, reserved FROM books WHERE isbn = ?";
            try (PreparedStatement selectBookStatement = connection.prepareStatement(selectBookSql)) {
                selectBookStatement.setString(1, isbn);
                ResultSet bookResultSet = selectBookStatement.executeQuery();

                if (bookResultSet.next()) {
                    int bookId = bookResultSet.getInt("id");
                    int available = bookResultSet.getInt("available");
                    int reserved = bookResultSet.getInt("reserved");

                    // Check if the library user with the given name exists
                    int libraryUserId = getLibraryUserIdByName(connection, libraryUserName);

                    if (libraryUserId != -1) {
                        if (available > 0) {
                            // The book is available, so create a borrowing record
                            String insertRecordSql = "INSERT INTO borrowingrecord (book_id, libraryuser_id, borrowdate, returndate) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement insertRecordStatement = connection.prepareStatement(insertRecordSql)) {
                                insertRecordStatement.setInt(1, bookId);
                                insertRecordStatement.setInt(2, libraryUserId);
                                insertRecordStatement.setTimestamp(3, new java.sql.Timestamp(borrowDate.getTime()));
                                insertRecordStatement.setTimestamp(4, new java.sql.Timestamp(returnDate.getTime()));
                                insertRecordStatement.executeUpdate();

                                // Update the book availability (-1) and reserved (+1)
                                String updateBookSql = "UPDATE books SET available = ?, reserved = ? WHERE id = ?";
                                try (PreparedStatement updateBookStatement = connection.prepareStatement(updateBookSql)) {
                                    updateBookStatement.setInt(1, available - 1);
                                    updateBookStatement.setInt(2, reserved + 1);
                                    updateBookStatement.setInt(3, bookId);
                                    updateBookStatement.executeUpdate();
                                }
                                String successMessage = "Book with ISBN '" + isbn + "' borrowed successfully";
                                System.out.println(successMessage);
                                return successMessage;
                            }
                        } else {
                            String errorMessage = "Book with ISBN '" + isbn + "' is not available for borrowing";
                            System.out.println(errorMessage);
                            return errorMessage;
                        }
                    } else {
                        String errorMessage = "Library user with name '" + libraryUserName + "' not found";
                        System.out.println(errorMessage);
                        return errorMessage;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Error: " + e.getMessage();
            System.out.println(errorMessage);
            return errorMessage;
        }

        String errorMessage = "Book with ISBN '" + isbn + "' not found";
        System.out.println(errorMessage);
        return errorMessage;
    }


    public String returnBook() {//ISBN

        return "Returned successfully";
    }


    public static int getLibraryUserIdByName(Connection connection, String libraryUserName) {
        int libraryUserId = -1;
        try {
            // Check if the library user with the given name exists
            String selectUserSql = "SELECT id FROM libraryuser WHERE name = ?";
            try (PreparedStatement selectUserStatement = connection.prepareStatement(selectUserSql)) {
                selectUserStatement.setString(1, libraryUserName);
                ResultSet userResultSet = selectUserStatement.executeQuery();

                if (userResultSet.next()) {
                    libraryUserId = userResultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libraryUserId;
    }

}
