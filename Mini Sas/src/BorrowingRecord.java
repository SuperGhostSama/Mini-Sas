import java.util.Date;

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
    public String borrowBook(Book book) {//ISBN

        return "Borrowed successfully";
    }

    public String returnBook() {//ISBN

        return "Returned successfully";
    }

}
