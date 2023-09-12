import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("Welcome To Library Management System");
            System.out.println("------------------------------------------------");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Reserve Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Available Books");
            System.out.println("8. View Reserved Books");
            System.out.println("9. Search For Book");
            System.out.println("10. Show Statistics");
            System.out.println("11. Manage Users");
            System.out.println("12. Manage Authors");
            System.out.println("13. Exit");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            scanner.nextLine();

            String choicePattern = "^[0-9]+$";

            if (choice.matches(choicePattern)) {
                int numericChoice = Integer.parseInt(choice);

                switch (numericChoice) {
                    case 1:
                        // Show Books
                        displayBooks(Book.showBooks());

                        System.out.println("Press Enter to return to the main menu...");
                        scanner.nextLine();
                        break;

                    case 2:
                        // Add Book
                        System.out.println("Enter Title:");
                        String title = scanner.nextLine();

                        // Validate title (letters and spaces only)
                        String titlePattern = "^[A-Za-z\\s]+$";
                        if (!title.matches(titlePattern)) {
                            System.out.println("Invalid title format. Please enter letters and spaces only.");
                            break;
                        }

                        System.out.println("Enter author Name:");
                        String authorName = scanner.nextLine();

                        // Validate author name (letters and spaces only)
                        String authorNamePattern = "^[A-Za-z\\s]+$";
                        if (!authorName.matches(authorNamePattern)) {
                            System.out.println("Invalid author name format. Please enter letters and spaces only.");
                            break;
                        }

                        Author author = new Author(authorName);

                        System.out.println("Enter isbn:");
                        String isbn = scanner.nextLine();

                        // Validate ISBN (letters, numbers, and hyphens only)
                        String isbnPattern = "^[A-Za-z0-9\\-]+$";
                        if (!isbn.matches(isbnPattern)) {
                            System.out.println("Invalid ISBN format. Please enter letters, numbers, and hyphens only.");
                            break;
                        }

                        System.out.println("Enter quantity:");
                        int quantity;
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity format. Please enter a valid number.");
                            break;
                        }

                        //set to 0
                        int available = 0;
                        int reserved = 0;
                        int lost = 0;

                        Book book = new Book(title, author, isbn, quantity, available, reserved, lost);
                        book.addBook();

                        break;



                    case 3:
                        // Edit Book
                        System.out.println("Enter Book ISBN To Edit:");
                        String isbnToEdit = scanner.nextLine();

                        Book editBook = new Book();

                        // Validate ISBN (letters, numbers, and hyphens only)
                        String editIsbnPattern = "^[A-Za-z0-9\\-]+$";

                        if (!isbnToEdit.matches(editIsbnPattern)) {
                            System.out.println("Invalid ISBN format. Please enter letters, numbers, and hyphens only.");
                            break;
                        }

                        if (editBook.checkIsbnExistence(isbnToEdit)) {
                            // More editing options
                            while (true) {
                                System.out.println("Select what you want to edit:");
                                System.out.println("1. Title");
                                System.out.println("2. ISBN");
                                System.out.println("3. Quantity");
                                System.out.println("4. Author Name");
                                System.out.println("5. Done Editing");

                                int editChoice;
                                try {
                                    editChoice = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid choice. Please enter a valid number.");
                                    break;
                                }

                                switch (editChoice) {
                                    case 1:
                                        // Edit Title
                                        System.out.println("Enter New Title:");
                                        String newTitle = scanner.nextLine();

                                        // Validate title (letters and spaces only)
                                        String editTitlePattern = "^[A-Za-z\\s]+$";
                                        if (!newTitle.matches(editTitlePattern)) {
                                            System.out.println("Invalid title format. Please enter letters and spaces only.");
                                            break;
                                        }

                                        String titleUpdateResult = editBook.updateTitle(isbnToEdit, newTitle);
                                        System.out.println(titleUpdateResult);
                                        break;
                                    case 2:
                                        // Edit ISBN
                                        System.out.println("Enter New ISBN:");
                                        String newIsbn = scanner.nextLine();

                                        // Validate ISBN (letters, numbers, and hyphens only)
                                        if (!newIsbn.matches(editIsbnPattern)) {
                                            System.out.println("Invalid ISBN format. Please enter letters, numbers, and hyphens only.");
                                            break;
                                        }

                                        String isbnUpdateResult = editBook.updateIsbn(isbnToEdit, newIsbn);
                                        System.out.println(isbnUpdateResult);
                                        break;
                                    case 3:
                                        // Edit Quantity
                                        System.out.println("Enter New Quantity:");
                                        int newQuantity;
                                        try {
                                            newQuantity = Integer.parseInt(scanner.nextLine());
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid quantity format. Please enter a valid number.");
                                            break;
                                        }

                                        String quantityUpdateResult = editBook.updateQuantity(isbnToEdit, newQuantity);
                                        System.out.println(quantityUpdateResult);
                                        break;
                                    case 4:
                                        // Edit Author Name
                                        System.out.println("Enter New Author Name:");
                                        String newAuthorName = scanner.nextLine();

                                        // Validate author name (letters and spaces only)
                                        String editAuthorNamePattern = "^[A-Za-z\\s]+$";
                                        if (!newAuthorName.matches(editAuthorNamePattern)) {
                                            System.out.println("Invalid author name format. Please enter letters and spaces only.");
                                            break;
                                        }

                                        String authorNameUpdateResult = editBook.updateAuthorName(isbnToEdit, newAuthorName);
                                        System.out.println(authorNameUpdateResult);
                                        break;
                                    case 5:
                                        System.out.println("------------------------------------------------");
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                        break;
                                }

                                if (editChoice == 5) {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("ISBN not found. Please enter a valid ISBN.");
                        }

                        break;



                    case 4:
                        // Delete Book
                        System.out.println("Enter ISBN To Delete:");
                        String isbnToDelete = scanner.nextLine();

                        String deleteIsbnPattern = "^[a-zA-Z0-9\\-]+$";

                        if (isbnToDelete.matches(deleteIsbnPattern)) {
                            String deleteResult = Book.deleteBook(isbnToDelete);

                        } else {
                            System.out.println("Invalid ISBN format. Please enter a valid ISBN.");
                        }

                        break;


                    case 5:
                        // Borrow Book
                        System.out.print("Enter the ISBN of the book: ");
                        String isbnToBorrow = scanner.nextLine();

                        System.out.print("Enter the name of the library user: ");
                        String libraryUserName = scanner.nextLine();

                        System.out.print("Enter the borrow date (yyyy-MM-dd HH:mm:ss): ");
                        String borrowDateStr = scanner.nextLine();
                        Date borrowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(borrowDateStr);

                        System.out.print("Enter the return date (yyyy-MM-dd HH:mm:ss): ");
                        String returnDateStr = scanner.nextLine();
                        Date returnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(returnDateStr);

                        String borrowResult = BorrowingRecord.borrowBook(isbnToBorrow, libraryUserName, borrowDate, returnDate);
                        System.out.println(borrowResult);
                        break;


                    case 6:
                        // Return Book
                        System.out.print("Enter the ID of the borrowing record: ");
                        int recordId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter the ISBN of the book: ");
                        String returnIsbn = scanner.nextLine();

                        String returnResult = BorrowingRecord.returnBook(recordId, returnIsbn);

                        System.out.println(returnResult);
                        break;


                    case 7:
                        // View Available Books
                        List<Book> availableBooks = Book.showAvailableBooks();
                        if (availableBooks.isEmpty()) {
                            System.out.println("No available books found.");
                        } else {
                            System.out.println("Available Books:");
                            for (Book availableBook : availableBooks) {
                                System.out.println(availableBook.toString());
                            }
                        }
                        break;

                    case 8:
                        // View Reserved Books
                        List<Book> reservedBooks = Book.showReservedBooks();
                        if (reservedBooks.isEmpty()) {
                            System.out.println("No reserved books found.");
                        } else {
                            System.out.println("Reserved Books:");
                            for (Book reservedBook : reservedBooks) {
                                System.out.println(reservedBook.toString());
                            }
                        }
                        break;


                    case 9:
                        // Nested switch case for Search
                        boolean optionsMenu = true;
                        while (optionsMenu) {
                            System.out.println("------------------------------------------------");
                            System.out.println("Search Menu:");
                            System.out.println("1. Search by Title");
                            System.out.println("2. Search by Author Name");
                            System.out.println("3. Return to Main Menu");
                            System.out.print("Enter your choice: ");

                            int optionChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline

                            switch (optionChoice) {
                                case 1:
                                    System.out.print("Enter title to search: ");
                                    String titleQuery = scanner.nextLine();

                                    List<Book> booksByTitle = Book.searchBooksByTitle(titleQuery);
                                    displayBooksSearch(booksByTitle);

                                    System.out.println("Press Enter to return to the options menu...");
                                    scanner.nextLine();

                                    break;
                                case 2:
                                    System.out.print("Enter author name to search: ");
                                    String authorQuery = scanner.nextLine();

                                    List<Book> booksByAuthor = Book.searchBooksByAuthor(authorQuery);
                                    displayBooksSearch(booksByAuthor);

                                    System.out.println("Press Enter to return to the options menu...");
                                    scanner.nextLine();
                                    break;
                                case 3:
                                    optionsMenu = false; // Return to the main menu
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                        break;

                    case 10:
                        // Statistics
                        int totalBooksStats = Book.getTotalBooks();
                        int availableBooksStats = Book.getAvailableBooks();
                        int reservedBooksStats = Book.getReservedBooks();
                        int lostBooksStats = Book.getLostBooks();
                        int totalUsersStats = Book.getTotalUsers();

                        System.out.println("Library Statistics:");
                        System.out.println("Total Books: " + totalBooksStats);
                        System.out.println("Available Books: " + availableBooksStats);
                        System.out.println("Reserved Books: " + reservedBooksStats);
                        System.out.println("Lost Books: " + lostBooksStats);
                        System.out.println("Total Users: " + totalUsersStats);

                        System.out.println("Press Enter to return to menu...");
                        scanner.nextLine();
                        break;


                    case 11:
                        // Nested switch case for user management
                        boolean userManagementMenu = true;
                        while (userManagementMenu) {
                            System.out.println("------------------------------------------------");
                            System.out.println("User Management Menu:");
                            System.out.println("1. View Users");
                            System.out.println("2. Add New User");
                            System.out.println("3. Delete User");
                            System.out.println("4. Return to Main Menu");
                            System.out.print("Enter your choice: ");

                            int userManagementChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (userManagementChoice) {
                                case 1:
                                    // View Users
                                    List<LibraryUser> users = LibraryUser.showUsers();
                                    for (LibraryUser user : users) {
                                        System.out.println("User ID: " + user.getId());
                                        System.out.println("Name: " + user.getName());
                                        System.out.println("Email: " + user.getEmail());
                                        System.out.println("Phone: " + user.getPhone());
                                        System.out.println("--------------------------");
                                    }
                                    break;
                                case 2:
                                    // Add New User
                                    System.out.print("Enter the user's name: ");
                                    String userName = scanner.nextLine();
                                    System.out.print("Enter the user's email: ");
                                    String userEmail = scanner.nextLine();
                                    System.out.print("Enter the user's phone: ");
                                    String userPhone = scanner.nextLine();
                                    String addUserResult = LibraryUser.addUser(userName, userEmail, userPhone);
                                    System.out.println(addUserResult);
                                    break;
                                case 3:
                                    // Delete User
                                    System.out.print("Enter the user ID to delete: ");
                                    int userIdToDelete = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character
                                    String deleteUserResult = LibraryUser.deleteUser(userIdToDelete);
                                    System.out.println(deleteUserResult);
                                    break;
                                case 4:
                                    // Return to Main Menu
                                    userManagementMenu = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a valid option.");
                                    break;
                            }
                        }
                        break;

                    case 12:
                        // Nested switch case for author management
                        boolean authorSubMenu = true;

                        while (authorSubMenu) {
                            System.out.println("------------------------------------------------");
                            System.out.println("Author Menu:");
                            System.out.println("1. Show Authors");
                            System.out.println("2. Add Author");
                            System.out.println("3. Update Author");
                            System.out.println("4. Delete Author");
                            System.out.println("5. Return to Main Menu");
                            System.out.print("Enter your choice: ");

                            int authorChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (authorChoice) {
                                case 1:
                                    // Show Authors
                                    List<Author> authors = Author.showAuthors();
                                    if (authors.isEmpty()) {
                                        System.out.println("No authors found.");
                                    } else {
                                        System.out.println("Authors:");
                                        for (Author author2 : authors) {
                                            System.out.println("ID: " + author2.getId() + ", Name: " + author2.getName());
                                        }
                                    }

                                    System.out.println("Press Enter to return to the Author menu...");
                                    scanner.nextLine();
                                    break;
                                case 2:
                                    // Add Author
                                    System.out.print("Enter the author's name: ");
                                    String authorNameToAdd = scanner.nextLine(); // Read author name from user input

                                    String addAuthorResult = Author.addAuthor(authorNameToAdd); // Call the addAuthor method

                                    System.out.println(addAuthorResult); // Print the result message

                                    System.out.println("Press Enter to return to the options menu...");
                                    scanner.nextLine();
                                    break;

                                case 3:
                                    // Update Author
                                    System.out.print("Enter the author's ID to edit: ");
                                    int authorIdToEdit = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character

                                    System.out.print("Enter the new name for the author: ");
                                    String newAuthorName = scanner.nextLine(); // Read the new author name from user input

                                    String editAuthorResult = Author.editAuthor(authorIdToEdit, newAuthorName); // Call the editAuthor method

                                    System.out.println(editAuthorResult); // Print the result message

                                    System.out.println("Press Enter to return to the Author menu...");
                                    scanner.nextLine();
                                    break;

                                case 4:
                                    // Delete Author
                                    System.out.print("Enter the author's ID to delete: ");
                                    int authorIdToDelete = scanner.nextInt();

                                    String deleteAuthorResult = Author.deleteAuthor(authorIdToDelete); // Call the deleteAuthor method

                                    System.out.println(deleteAuthorResult); // Print the result message

                                    System.out.println("Press Enter to return to the Author menu...");
                                    scanner.nextLine();
                                    break;
                                case 5:
                                    // Return to Main Menu
                                    authorSubMenu = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a valid option.");
                            }
                        }
                        break;

                    case 13:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                }
            }else{
                System.out.println("Please enter a valid number.");

            }
        }




    }

    // Helper methods for display
    private static void displayBooksSearch(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void displayBooks(List<Book> books) {
        for (Book book :Book.showBooks() ) {
            System.out.println(book.toString());
        }
    }
}
