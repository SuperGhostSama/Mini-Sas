import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome To Library Management System");
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
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    displayBooks(Book.showBooks());

                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("dkhel Title :");
                    String title = scanner.nextLine();

                    System.out.println("dkhel author id :");
                    int author_id = Integer.parseInt(scanner.nextLine());
                    Author author = new Author(author_id);

                    System.out.println("dkhel isbn :");
                    String isbn = scanner.nextLine();

                    System.out.println("dkhel quantity :");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    System.out.println("dkhel available :");
                    int available = Integer.parseInt(scanner.nextLine());

                    System.out.println("dkhel reserved :");
                    int reserved = Integer.parseInt(scanner.nextLine());

                    System.out.println("dkhel lost :");
                    int lost = Integer.parseInt(scanner.nextLine());

                    Book book = new Book(title, author, isbn, quantity, available, reserved, lost);
                    book.addBook();

                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 3:
                    //
                    System.out.println("Enter Book Id To Edit :");
                    int editId = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter New Title :");
                    String newTitle = scanner.nextLine();

                    System.out.println("Enter New author id :");
                    int newAuthor_id = Integer.parseInt(scanner.nextLine());
                    author = new Author(newAuthor_id);

                    System.out.println("Enter New isbn :");
                    String newIsbn = scanner.nextLine();

                    System.out.println("Enter New quantity :");
                    int newQuantity = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter New available :");
                    int newAvailable = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter New reserved :");
                    int newReserved = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter New lost :");
                    int newLost = Integer.parseInt(scanner.nextLine());

                    Book bookToEdit= new Book(editId,newTitle,author, newIsbn,newQuantity,newAvailable,newReserved,newLost);
                    bookToEdit.editBook();

                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 4:
                    //
                    System.out.println("Enter Book Id To Delete :");
                    int bookid = Integer.parseInt(scanner.nextLine());

                    String deleteResult = Book.deleteBook(bookid);
                    System.out.println(deleteResult);


                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case 5:
                    //
                    break;
                case 6:
                    //
                    break;
                case 7:
                    //
                    break;
                case 8:
                    //
                    break;
                case 9:
                    boolean optionsMenu = true;
                    while (optionsMenu) {
                        System.out.println("Options Menu:");
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
                    //
                    break;
                case 11:
                    // Nested switch case for user management
                    boolean userManagementMenu = true;
                    while (userManagementMenu) {
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
