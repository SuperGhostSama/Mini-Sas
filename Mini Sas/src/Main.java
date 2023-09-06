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
            System.out.println("11. Add User");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //Book.showBooks();//not working yet
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
                    break;
                case 3:
                    //

                    break;
                case 4:
                    //
                    System.out.println("Enter Book Id To Delete :");
                    int bookid = Integer.parseInt(scanner.nextLine());

                    String deleteResult = Book.deleteBook(bookid);
                    System.out.println(deleteResult);
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
                    //
                    break;
                case 10:
                    //
                    break;
                case 11:
                    //
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


}
