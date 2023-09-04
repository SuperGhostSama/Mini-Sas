import java.util.List;
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


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    //METHODS
    public List<Book> showBook(Book book){
        //
        return null;
    }


    public String addBook(){
        //
        return "Added successfully";
    }

    public String editBook(int id, String title, Author author, String isbn, int quantity, int available, int reserved, int lost){
        //
        return "Edited successfully";
    }

    public String deleteBook(int id){
        //
        return "Deleted successfully";
    }
}
